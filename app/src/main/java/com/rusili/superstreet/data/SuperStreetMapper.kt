package com.rusili.superstreet.data

import com.rusili.superstreet.domain.common.*
import com.rusili.superstreet.domain.common.flag.Magazine
import com.rusili.superstreet.domain.common.flag.Type
import com.rusili.superstreet.domain.common.header.Image
import com.rusili.superstreet.domain.common.header.Title
import com.rusili.superstreet.domain.common.util.ATags
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class SuperStreetMapper {

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
        val flagMag = element.select(ATags.COMMON.A.value)[0]
        val flagType = element.select(ATags.COMMON.A.value)[1]

        val magazine = buildMagazine(flagMag)
        val type = buildType(flagType)

        return Flag(magazine, type)
    }

    private fun parseHeaderElement(element: Element): Header {
        val header = element.select(ATags.COMMON.A.value)[0]

        val title = buildTitle(header)
        val image = buildImage(header)
        val desc = element.select(ATags.HEADER.DESC.value).text()

        return Header(title, image, desc)
    }

    private fun parseFooterElement(element: Element): Footer {
        val author = element.select(ATags.FOOTER.AUTHOR.value).text()
        val timestamp = element.select(ATags.FOOTER.TIMESTAMP.value).text()

        return Footer(author, timestamp)
    }

    private fun buildMagazine(flagMag: Element): Magazine {
        val flagMagValue = flagMag.attr(ATags.FLAG.TITLE.value).toString()
        val flagMaghrefEndpoint = flagMag.attr(ATags.FLAG.HREF.value).toString()
        return Magazine(flagMagValue, flagMaghrefEndpoint)
    }

    private fun buildType(flagType: Element): Type {
        val flagTypeValue = flagType.textNodes()[0].text()
        val flagTypehrefEndpoint = flagType.attr(ATags.FLAG.HREF.value).toString()
        return Type(flagTypeValue, flagTypehrefEndpoint)
    }

    private fun buildTitle(header: Element): Title {
        val title = header.attr(ATags.HEADER.TITLE.value)
        val hrefEndpoint = header.attr(ATags.HEADER.HREF.value)

        return Title(title, hrefEndpoint)
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