package com.rusili.superstreet.jsoup.parsing

import com.rusili.superstreet.common.models.Header
import com.rusili.superstreet.common.models.ImageUrl
import com.rusili.superstreet.common.models.header.HeaderImage
import com.rusili.superstreet.common.models.header.Title
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import com.rusili.superstreet.previewlist.domain.CardSize
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.util.ArrayList
import javax.inject.Inject

/**
 * Parses an Html file to PreviewList related models.
 */
class PreviewListParser @Inject constructor(private val commonParser: CommonParser) {

    fun parseToList(doc: Document): List<ArticlePreviewModel> {
        val previewsList = ArrayList<ArticlePreviewModel>()

        val mainColumn = doc.getElementsByClass(LIST.MAIN_COLUMN.value).first()
        val topStoryElement = mainColumn.getElementsByClass(LIST.TOP_STORY.value).first()
        val stories = mainColumn.getElementsByClass(LIST.STORIES_CONTAINER.value).first()

        topStoryElement?.let {
            val topStoryFlag = commonParser.parseFlagElement(topStoryElement)
            val topStoryHeader = parseFeatureHeaderElement(topStoryElement)
            val topStoryFooter = commonParser.parseFooterElement(topStoryElement)

            previewsList.add(
                ArticlePreviewModel(
                    topStoryFlag,
                    topStoryHeader,
                    topStoryFooter,
                    CardSize.Large
                )
            )
        }

        stories.children().firstOrNull { story ->
            story.hasClass(LIST.PART_ITEM.value) || story.hasClass(LIST.PART_HERO.value)
        }?.let { story ->
            val flag = commonParser.parseFlagElement(story)
            val header = parseFeatureHeaderElement(story)
            val footer = commonParser.parseFooterElement(story)
            val size = when {
                story.hasClass(LIST.PART_HERO.value) -> CardSize.Large
                else -> CardSize.Small
            }

            previewsList.add(
                ArticlePreviewModel(
                    flag,
                    header,
                    footer,
                    size
                )
            )
        }
        return previewsList
    }

    private fun parseFeatureHeaderElement(element: Element): Header {
        // TitlePreview
        val infoNode = element.select(PREVIEW_HEADER.INFO.value).first()
        val titleNode = infoNode.select(COMMON.A.value).first()

        val titleValue = titleNode.attr(COMMON.TITLE.value)
        val titleHrefEndpoint = titleNode.attr(COMMON.HREF.value)
        val title = Title(titleValue, titleHrefEndpoint)

        // Desc
        val desc = element.select(PREVIEW_HEADER.DESC.value).text()

        // HeaderImage:
        val imgTitle: String
        val imgSrc: String

        val nonFeatureImageNode = infoNode.select(COMMON.IMG.value)        // For non-feature stories:
        if (nonFeatureImageNode.isNotEmpty()) {
            imgTitle = nonFeatureImageNode.first().attr(PREVIEW_HEADER.DATA_ALT.value)
            imgSrc = nonFeatureImageNode.first().attr(PREVIEW_HEADER.DATA_SRC.value)
        } else {
            val imageNode = element.children().second().select(COMMON.A.value)            // For feature stories:
            val featureImageNode = imageNode.select(COMMON.IMG.value)

            // Top Story:
            imgTitle = featureImageNode.attr(PREVIEW_HEADER.ALT.value).ifBlank {
                featureImageNode.attr(COMMON.TITLE.value)
            }
            imgSrc = featureImageNode.attr(COMMON.SRC.value).ifBlank {
                featureImageNode.attr(PREVIEW_HEADER.DATA_SRC.value)
            }
        }

        return Header(
            title,
            HeaderImage(
                imgTitle,
                ImageUrl(imgSrc.trim())),
            desc
        )
    }
}
