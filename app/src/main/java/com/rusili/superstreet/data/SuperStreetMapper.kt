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

        val flags = doc.getElementsByClass(ATags.COMMON.FLAG.value)
        val previews = doc.getElementsByClass(ATags.COMMON.INFO.value)

        for (i in previews.indices) {
            val preview = previews[i]

            val flag = parseFlagElement(flags[i])
            val header = parsePreviewHeaderElement(preview)
            val footer = parseFooterElement(preview)

            val articlePreview = ArticlePreviewModel(flag, header, footer)
            previewsList.add(articlePreview)
        }

        return previewsList
    }

    fun parseToArticle(doc: Document): ArticleFullModel {
        val flags = doc.getElementsByClass(ATags.COMMON.FLAG.value)
        val article = doc.getElementsByClass(ATags.COMMON.INFO.value)[0]
        val image = doc.getElementsByClass("page-schema")[1]

        val flag = parseFlagElement(flags[0])
        val header = parseArticleHeaderElement(article, image)
//        val body = parseArticleBody()
        val footer = parseFooterElement(article)

        val articleModel = ArticleFullModel(flag, header, footer)

        return articleModel
    }

    private fun parseFlagElement(element: Element): Flag {
        // Magazine
        val flagMag = element.select(ATags.COMMON.A.value)[0]
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
            flagTypeValue = flagType.textNodes()[0].text()      // For Preview parsing
        }

        return flagMapper.getType(flagTypeValue)
    }

    private fun parsePreviewHeaderElement(element: Element): Header {
        val header = element.select(ATags.COMMON.A.value)[0]

        // TitlePreview
        val titleValue = header.attr(ATags.HEADER.TITLE.value)
        val titlehrefEndpoint = header.attr(ATags.COMMON.HREF.value)
        val title = Title(titleValue, titlehrefEndpoint)

        // Desc
        val desc = element.select(ATags.HEADER.DESC.value).text()

        // Image: Two types
        val image = buildPreviewImage(header)

        return Header(title, image, desc)
    }

    private fun parseArticleHeaderElement(element: Element, imageEle: Element): Header {
        val header = element.getElementsByClass(ATags.COMMON.INFO.value)[0]

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
            imgSrc = img[0].attr(ATags.HEADER.IMG_SRC.value)
            imgTitle = img[0].attr(ATags.HEADER.IMG_TITLE.value)
        }

        return Image(imgSrc, imgTitle)
    }

    private fun buildArticleImage(image: Element): Image {
        val itemprop = image.getElementsByClass("img-wrap")[0].select("a")[0]
        val imgTitle = itemprop.attr("title")
        val imgOriginal = itemprop.attr("href")
        val imgSmall = itemprop.select("img").attr("src")

        return Image(imgSmall, imgTitle)
    }
}