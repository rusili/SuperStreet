package com.rusili.superstreet.jsoup.parsing

import com.rusili.superstreet.common.models.Header
import com.rusili.superstreet.common.models.header.HeaderImage
import com.rusili.superstreet.common.models.header.Title
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import com.rusili.superstreet.previewlist.domain.CardSize
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
            previewsList.add(
                ArticlePreviewModel(
                    flag = commonParser.parseFlagElement(it),
                    header = parseFeatureHeaderElement(it),
                    footer = commonParser.parseFooterElement(it),
                    size = CardSize.Large)
            )
        }

        for (story in stories.children()) {
            if (story.hasClass(LIST.PART_ITEM.value) || story.hasClass(LIST.PART_HERO.value)) {
                previewsList.add(
                    ArticlePreviewModel(
                        flag = commonParser.parseFlagElement(story),
                        header = parseFeatureHeaderElement(story),
                        footer = commonParser.parseFooterElement(story),
                        size = when {
                            story.hasClass(LIST.PART_HERO.value) -> CardSize.Large
                            else -> CardSize.Small
                        })
                )
            }
        }

        return previewsList
    }

    private fun parseFeatureHeaderElement(element: Element): Header {
        val infoNode = element.select(PREVIEW_HEADER.INFO.value).first()

        return Header(
            title = infoNode.select(COMMON.A.value).first().let {
                Title(
                    it.attr(COMMON.TITLE.value),
                    it.attr(COMMON.HREF.value)
                )
            },
            headerImage = parseHeaderImage(infoNode, element),
            desc = element.select(PREVIEW_HEADER.DESC.value).text())
    }

    private fun parseHeaderImage(
        infoNode: Element,
        element: Element
    ): HeaderImage {
        var imgTitle: String
        var imgSrc: String

        val nonFeatureImageNode = infoNode.select(COMMON.IMG.value)                  // For non-feature stories:
        if (nonFeatureImageNode.isNotEmpty()) {
            imgTitle = nonFeatureImageNode.first().attr(PREVIEW_HEADER.DATA_ALT.value)
            imgSrc = nonFeatureImageNode.first().attr(PREVIEW_HEADER.DATA_SRC.value)
        } else {
            val featureImageNode = element.children()[1].select(COMMON.A.value)     // For feature stories:
                .select(COMMON.IMG.value)

            // Top Story:
            imgTitle = featureImageNode.attr(PREVIEW_HEADER.ALT.value)
            imgSrc = featureImageNode.attr(COMMON.SRC.value)

            // Feature Stories:
            if (imgTitle.isBlank() && imgSrc.isBlank()) {
                imgTitle = featureImageNode.attr(COMMON.TITLE.value)
                imgSrc = featureImageNode.attr(PREVIEW_HEADER.DATA_SRC.value)
            }
        }

        return HeaderImage(imgTitle, imgSrc)
    }
}
