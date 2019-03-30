package com.rusili.superstreet.jsoup.parsing

import com.rusili.superstreet.common.models.Flag
import com.rusili.superstreet.common.models.Footer
import com.rusili.superstreet.common.models.Magazine
import com.rusili.superstreet.common.models.Type
import com.rusili.superstreet.common.models.footer.Author
import org.jsoup.nodes.Element
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Base class for parsing an Html document to Super Street models.
 * Parses:
 *  - Flag(Magazine & Type)
 *  - Footer(Author & Date)
 */
class CommonParser {

    fun parseFlagElement(element: Element): Flag =
        Flag(
            parseMagazine(element),
            parseType(element)
        )

    fun parseFooterElement(element: Element): Footer =
        Footer(
            parseAuthor(element),
            parseDate(element)
        )

    private fun parseMagazine(element: Element): Magazine =
        Magazine.fromString(
            element.select(COMMON.A.value)
                .first()
                .attr(FLAG.TITLE.value)
        )

    private fun parseType(element: Element): Type {
        val flagType = element.select(COMMON.A.value).second()
        var flagTypeValue = flagType.select(FLAG.SPAN_LABEL.value).text()    // For Article parsing
        if (flagTypeValue.isBlank()) {
            flagTypeValue = flagType.textNodes().first().text()      // For Preview parsing
        }
        return Type.fromString(flagTypeValue)
    }

    private fun parseAuthor(element: Element): Author {
        var href = ""
        var value = element.select(FOOTER.AUTHOR_CONTRIBUTING_1.value).text()
        if (value.isBlank()) {
            value = element.select(FOOTER.AUTHOR_CONTIBUTING_2.value).text()
        }
        if (value.isBlank()) {
            val ele = element
                .select(FOOTER.AUTHOR_STAFF_DIV.value)
                .select(FOOTER.AUTHOR_STAFF_ATTR.value)
            value = ele.text()
            href = ele.attr(COMMON.HREF.value)
        }
        return Author(value, href)
    }

    private fun parseDate(element: Element): Date =
        SimpleDateFormat(FOOTER.TIMESTAMP_FORMAT.value, Locale.ENGLISH)
            .parse(element
                .select(FOOTER.TIMESTAMP.value)
                .text())
}
