package com.rusili.superstreet.data

import com.rusili.superstreet.data.util.ATags
import com.rusili.superstreet.domain.article.ArticleFullModel
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import com.rusili.superstreet.domain.models.Flag
import com.rusili.superstreet.domain.models.Footer
import com.rusili.superstreet.domain.models.Header
import com.rusili.superstreet.domain.models.flag.Type
import com.rusili.superstreet.domain.models.footer.Author
import com.rusili.superstreet.domain.models.header.Image
import com.rusili.superstreet.domain.models.header.Title
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Parses an Html document to Super Street models.
 */
class SuperStreetMapper @Inject constructor(private val flagMapper: FlagMapper) {

    fun parseToList(doc: Document): List<ArticlePreviewModel> {
        val previewsList = ArrayList<ArticlePreviewModel>()

        val mainColumn = doc.getElementsByClass("main-column").first()
        val topStoryElement = mainColumn.getElementsByClass("mod-top-story").first()
        val stories = mainColumn.getElementsByClass("mod-list-homepage mod-list-container").first()

        val topStoryFlag = parseFlagElement(topStoryElement)
        val topStoryHeader = parseFeatureHeaderElement(topStoryElement)
        val topStoryFooter = parseFooterElement(topStoryElement)
        val topStoryArticlePreviewModel = ArticlePreviewModel(topStoryFlag, topStoryHeader, topStoryFooter)

        previewsList.add(topStoryArticlePreviewModel)

        for (story in stories.children()) {
            if (story.hasClass("part-list-item") || story.hasClass("part-hero")) {
                val flag = parseFlagElement(story)
                val header = parseFeatureHeaderElement(story)
                val footer = parseFooterElement(story)

                val articlePreview = ArticlePreviewModel(flag, header, footer)
                previewsList.add(articlePreview)
            }
        }
        return previewsList
    }

    fun parseToArticle(doc: Document): ArticleFullModel {
        val flags = doc.getElementsByClass(ATags.COMMON.FLAG.value)
        val article = doc.getElementsByClass(ATags.COMMON.INFO.value).first()
        val image = doc.getElementsByClass("page-schema")[1]

        val flag = parseFlagElement(flags.first())
        val header = parseArticleHeaderElement(article, image)
//        val body = parseArticleBody()
        val footer = parseFooterElement(article)

        val articleModel = ArticleFullModel(flag, header, footer)

        return articleModel
    }

    private fun parseFlagElement(element: Element): Flag {
        // Magazine
        val flagMag = element.select(ATags.COMMON.A.value).first()
        val flagMagValue = flagMag.attr(ATags.FLAG.TITLE.value).toString()
        val magazine = flagMapper.getMagazine(flagMagValue)

        // Type
        val type = buildType(element)

        return Flag(magazine, type)
    }

    private fun buildType(element: Element): Type {
        val flagType = element.select(ATags.COMMON.A.value)[1]

        var flagTypeValue = flagType.select("span.label").text()    // For Article parsing
        if (flagTypeValue.isBlank()) {
            flagTypeValue = flagType.textNodes().first().text()      // For Preview parsing
        }

        return flagMapper.getType(flagTypeValue)
    }

    private fun parseFeatureHeaderElement(element: Element): Header {
        // TitlePreview
        val infoNode = element.select("div.info").first()
        val titleNode = infoNode.select(ATags.COMMON.A.value).first()

        val titleValue = titleNode.attr(ATags.HEADER.TITLE.value)
        val titleHrefEndpoint = titleNode.attr(ATags.COMMON.HREF.value)
        val title = Title(titleValue, titleHrefEndpoint)

        // Desc
        val desc = element.select(ATags.HEADER.DESC.value).text()

        // Image:
        var imageTitle: String
        var imageHref: String

        val nonFeatureImageNode = infoNode.select(ATags.HEADER.IMG.value)        // For non-feature stories:
        if (nonFeatureImageNode.isNotEmpty()) {
            imageTitle = nonFeatureImageNode.first().attr(ATags.HEADER.DATA_ALT.value)
            imageHref = nonFeatureImageNode.first().attr(ATags.HEADER.DATA_SRC.value)
        } else {
            val imageNode = element.children()[1].select(ATags.COMMON.A.value)            // For feature stories:
            val featureImageNode = imageNode.select("img")

            // Top Story:
            imageTitle = featureImageNode.attr("alt") // "title"
            imageHref = featureImageNode.attr("src") // "data-src"

            // Feature Stories:
            if (imageTitle.isBlank() && imageHref.isBlank()) {
                imageTitle = featureImageNode.attr("title")
                imageHref = featureImageNode.attr("data-src")
            }
        }
        val image = Image(imageTitle, imageHref)

        return Header(title, image, desc)
    }

    private fun parseArticleHeaderElement(element: Element, imageEle: Element): Header {
        val header = element.getElementsByClass(ATags.COMMON.INFO.value).first()

        // TitlePreview
        val titleValue = header.select("h1.title").text()
        val title = Title(titleValue, "")

        // Desc
        val desc = header.select("h2.desc").text()

        // Image
        val image = buildArticleImage(imageEle)

        return Header(title, image, desc)
    }

    private fun parseFooterElement(element: Element): Footer {
        // Author
        var href = ""
        var value = element.select(ATags.FOOTER.AUTHOR_CONTRIBUTING_1.value).text()
        if (value.isBlank()) {
            value = element.select(ATags.FOOTER.AUTHOR_CONTIBUTING_2.value).text()
        }
        if (value.isBlank()) {
            val ele = element.select(ATags.FOOTER.AUTHOR_STAFF_DIV.value)
                    .select(ATags.FOOTER.AUTHOR_STAFF_ATTR.value)
            value = ele.text()
            href = ele.attr(ATags.COMMON.HREF.value).toString()
        }

        // Timestamp
        val timestamp = element.select(ATags.FOOTER.TIMESTAMP.value).text()
        val format = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
        val date = format.parse(timestamp)

        return Footer(Author(value, href), date)
    }

    private fun buildPreviewImage(header: Element): Image {
        var imgSrc = ""
        var imgTitle = ""

        val img = header.select(ATags.HEADER.IMG.value)
        if (img.isNotEmpty()) {
            imgSrc = img.first().attr(ATags.HEADER.DATA_ALT.value)
            imgTitle = img.first().attr(ATags.HEADER.DATA_SRC.value)
        }

        return Image(imgSrc, imgTitle)
    }

    private fun buildArticleImage(image: Element): Image {
        val itemprop = image.getElementsByClass("img-wrap").first().select("a").first()
        val imgTitle = itemprop.attr("title")
        val imgOriginal = itemprop.attr("href")
        val imgSmall = itemprop.select("img").attr("src")

        return Image(imgTitle, imgSmall)
    }
}