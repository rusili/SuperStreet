package com.rusili.superstreet.data.list

import com.rusili.superstreet.data.util.*
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import com.rusili.superstreet.domain.models.Header
import com.rusili.superstreet.domain.models.header.Image
import com.rusili.superstreet.domain.models.header.Title
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.util.*
import javax.inject.Inject

/**
 * Parses an Html document to Super Street models.
 */
class PreviewListMapper @Inject constructor(flagMapper: FlagMapper) : BaseMapper(flagMapper) {

    fun parseToList(doc: Document): List<ArticlePreviewModel> {
        val previewsList = ArrayList<ArticlePreviewModel>()

        val mainColumn = doc.getElementsByClass(LIST.MAIN_COLUMN.value).first()
        val topStoryElement = mainColumn.getElementsByClass(LIST.TOP_STORY.value).first()
        val stories = mainColumn.getElementsByClass(LIST.STORIES_CONTAINER.value).first()

        val topStoryFlag = parseFlagElement(topStoryElement)
        val topStoryHeader = parseFeatureHeaderElement(topStoryElement)
        val topStoryFooter = parseFooterElement(topStoryElement)
        val topStoryArticlePreviewModel = ArticlePreviewModel(topStoryFlag, topStoryHeader, topStoryFooter)

        previewsList.add(topStoryArticlePreviewModel)

        for (story in stories.children()) {
            if (story.hasClass(LIST.PART_ITEM.value) || story.hasClass(LIST.PART_HERO.value)) {
                val flag = parseFlagElement(story)
                val header = parseFeatureHeaderElement(story)
                val footer = parseFooterElement(story)

                val articlePreview = ArticlePreviewModel(flag, header, footer)
                previewsList.add(articlePreview)
            }
        }
        return previewsList
    }

    private fun parseFeatureHeaderElement(element: Element): Header {
        // TitlePreview
        val infoNode = element.select(PREVIEW_HEADER.INFO.value).first()
        val titleNode = infoNode.select(COMMON.A.value).first()

        val titleValue = titleNode.attr(PREVIEW_HEADER.TITLE.value)
        val titleHrefEndpoint = titleNode.attr(COMMON.HREF.value)
        val title = Title(titleValue, titleHrefEndpoint)

        // Desc
        val desc = element.select(PREVIEW_HEADER.DESC.value).text()

        // Image:
        var imageTitle: String
        var imageHref: String

        val nonFeatureImageNode = infoNode.select(PREVIEW_HEADER.IMG.value)        // For non-feature stories:
        if (nonFeatureImageNode.isNotEmpty()) {
            imageTitle = nonFeatureImageNode.first().attr(PREVIEW_HEADER.DATA_ALT.value)
            imageHref = nonFeatureImageNode.first().attr(PREVIEW_HEADER.DATA_SRC.value)
        } else {
            val imageNode = element.children()[1].select(COMMON.A.value)            // For feature stories:
            val featureImageNode = imageNode.select(PREVIEW_HEADER.IMG.value)

            // Top Story:
            imageTitle = featureImageNode.attr(PREVIEW_HEADER.ALT.value)
            imageHref = featureImageNode.attr(PREVIEW_HEADER.SRC.value)

            // Feature Stories:
            if (imageTitle.isBlank() && imageHref.isBlank()) {
                imageTitle = featureImageNode.attr(PREVIEW_HEADER.TITLE.value)
                imageHref = featureImageNode.attr(PREVIEW_HEADER.DATA_SRC.value)
            }
        }
        val image = Image(imageTitle, imageHref)

        return Header(title, image, desc)
    }
}