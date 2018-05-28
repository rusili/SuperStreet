package com.rusili.superstreet.data

import com.rusili.superstreet.domain.list.ArticlePreviewModel
import com.rusili.superstreet.domain.models.Flag
import com.rusili.superstreet.domain.models.Footer
import com.rusili.superstreet.domain.models.Header
import com.rusili.superstreet.domain.models.footer.Author
import com.rusili.superstreet.domain.models.header.Image
import com.rusili.superstreet.domain.models.header.Title
import com.rusili.superstreet.domain.util.ATags
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class SuperStreetMapper(private val flagMapper: FlagMapper) {

    fun parseToList(doc: Document): List<ArticlePreviewModel> {
        val previewsList = ArrayList<ArticlePreviewModel>()

        val flags = doc.getElementsByClass(ATags.COMMON.FLAG.value)
        val previews = doc.getElementsByClass(ATags.COMMON.INFO.value)

        for (i in previews.indices) {
            val preview = previews[i]

            val flag = parseFlagElement(flags[i])
            val header = parseHeaderElement(preview)
            val footer = parseFooterElement(preview)

            val articlePreview = ArticlePreviewModel(flag, header, footer)
            previewsList.add(articlePreview)
        }

        return previewsList
    }

    private fun parseFlagElement(element: Element): Flag {
        // Magazine
        val flagMag = element.select(ATags.COMMON.A.value)[0]
        val flagMagValue = flagMag.attr(ATags.FLAG.TITLE.value).toString()
        val magazine = flagMapper.getMagazine(flagMagValue)

        // Type
        val flagType = element.select(ATags.COMMON.A.value)[1]
        val flagTypeValue = flagType.textNodes()[0].text()
        val type = flagMapper.getType(flagTypeValue)

        return Flag(magazine, type)
    }

    private fun parseHeaderElement(element: Element): Header {
        val header = element.select(ATags.COMMON.A.value)[0]

        // Title
        val titleValue = header.attr(ATags.HEADER.TITLE.value)
        val titlehrefEndpoint = header.attr(ATags.COMMON.HREF.value)
        val title = Title(titleValue, titlehrefEndpoint)

        // Desc
        val desc = element.select(ATags.HEADER.DESC.value).text()

        // Image
        val image = buildImage(header)

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

    private fun buildImage(header: Element): Image {
        var imgSrc = ""
        var imgTitle = ""

        val img = header.select(ATags.HEADER.IMG.value)
        if (img.isNotEmpty()) {
            imgSrc = img[0].attr(ATags.HEADER.IMG_SRC.value)
            imgTitle = img[0].attr(ATags.HEADER.IMG_TITLE.value)
        }

        return Image(imgSrc, imgTitle)
    }
}