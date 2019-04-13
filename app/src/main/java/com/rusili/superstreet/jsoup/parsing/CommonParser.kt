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
 * Base class for parsing an Html document f Super Street models.
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

        // For Article parsing
        val flagTypeValue = flagType.select(FLAG.SPAN_LABEL.value).text().ifBlank {
            // For Preview parsing
            flagType.textNodes().first().text()
        }
        return Type.fromString(flagTypeValue)
    }

    private fun parseAuthor(element: Element): Author {
        var href = ""
        val value = element.select(FOOTER.AUTHOR_CONTRIBUTING_1.value).text()
            .ifBlank {
                element.select(FOOTER.AUTHOR_CONTIBUTING_2.value).text()
            }.ifBlank {
                val ele = element
                    .select(FOOTER.AUTHOR_STAFF_DIV.value)
                    .select(FOOTER.AUTHOR_STAFF_ATTR.value)
                href = ele.attr(COMMON.HREF.value)
                ele.text()
            }

        return Author(value, href)
    }

    private fun parseDate(element: Element): Date =
        SimpleDateFormat(FOOTER.TIMESTAMP_FORMAT.value, Locale.ENGLISH)
            .parse(
                element
                    .select(FOOTER.TIMESTAMP.value)
                    .text()
            )
}
