package com.rusili.superstreet.data.list

import com.rusili.superstreet.data.util.*
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import com.rusili.superstreet.domain.list.CardSize
import com.rusili.superstreet.domain.models.Header
import com.rusili.superstreet.domain.models.header.HeaderImage
import com.rusili.superstreet.domain.models.header.Title
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.util.*
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
            val topStoryArticlePreviewModel = ArticlePreviewModel(topStoryFlag, topStoryHeader, topStoryFooter, CardSize.Large)
            previewsList.add(topStoryArticlePreviewModel)
        }

        for (story in stories.children()) {
            if (story.hasClass(LIST.PART_ITEM.value) || story.hasClass(LIST.PART_HERO.value)) {
                val flag = commonParser.parseFlagElement(story)
                val header = parseFeatureHeaderElement(story)
                val footer = commonParser.parseFooterElement(story)
                val size = when {
                    story.hasClass(LIST.PART_HERO.value) -> CardSize.Large
                    else -> CardSize.Small
                }

                val articlePreview = ArticlePreviewModel(flag, header, footer, size)
                previewsList.add(articlePreview)
            }
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
        var imgTitle: String
        var imgSrc: String

        val nonFeatureImageNode = infoNode.select(COMMON.IMG.value)        // For non-feature stories:
        if (nonFeatureImageNode.isNotEmpty()) {
            imgTitle = nonFeatureImageNode.first().attr(PREVIEW_HEADER.DATA_ALT.value)
            imgSrc = nonFeatureImageNode.first().attr(PREVIEW_HEADER.DATA_SRC.value)
        } else {
            val imageNode = element.children()[1].select(COMMON.A.value)            // For feature stories:
            val featureImageNode = imageNode.select(COMMON.IMG.value)

            // Top Story:
            imgTitle = featureImageNode.attr(PREVIEW_HEADER.ALT.value)
            imgSrc = featureImageNode.attr(COMMON.SRC.value)

            // Feature Stories:
            if (imgTitle.isBlank() && imgSrc.isBlank()) {
                imgTitle = featureImageNode.attr(COMMON.TITLE.value)
                imgSrc = featureImageNode.attr(PREVIEW_HEADER.DATA_SRC.value)
            }
        }
        val image = HeaderImage(imgTitle, imgSrc)

        return Header(title, image, desc)
    }
}