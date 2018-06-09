package com.rusili.superstreet.data.util

import com.rusili.superstreet.domain.models.Flag
import com.rusili.superstreet.domain.models.Footer
import com.rusili.superstreet.domain.models.flag.Magazine
import com.rusili.superstreet.domain.models.flag.Type
import com.rusili.superstreet.domain.models.footer.Author
import org.jsoup.nodes.Element
import java.text.SimpleDateFormat
import java.util.*

/**
 * Base class for parsing an Html document to Super Street models.
 * Parses:
 *  - Flag(Magazine & Type)
 *  - Footer(Author & Date)
 */
open class BaseMapper(private val flagMapper: FlagMapper) {

    fun parseFlagElement(element: Element): Flag {
        val magazine = parseMagazine(element)
        val type = parseType(element)

        return Flag(magazine, type)
    }

    fun parseFooterElement(element: Element): Footer {
        val author = parseAuthor(element)
        val date = parseDate(element)

        return Footer(author, date)
    }

    private fun parseMagazine(element: Element): Magazine {
        val flagMag = element.select(COMMON.A.value).first()
        val flagMagValue = flagMag.attr(FLAG.TITLE.value)
        return flagMapper.getMagazine(flagMagValue)
    }

    private fun parseType(element: Element): Type {
        val flagType = element.select(COMMON.A.value)[1]
        var flagTypeValue = flagType.select(FLAG.SPAN_LABEL.value).text()    // For Article parsing
        if (flagTypeValue.isBlank()) {
            flagTypeValue = flagType.textNodes().first().text()      // For Preview parsing
        }
        return flagMapper.getType(flagTypeValue)
    }

    private fun parseAuthor(element: Element): Author {
        var href = ""
        var value = element.select(FOOTER.AUTHOR_CONTRIBUTING_1.value).text()
        if (value.isBlank()) {
            value = element.select(FOOTER.AUTHOR_CONTIBUTING_2.value).text()
        }
        if (value.isBlank()) {
            val ele = element.select(FOOTER.AUTHOR_STAFF_DIV.value)
                    .select(FOOTER.AUTHOR_STAFF_ATTR.value)
            value = ele.text()
            href = ele.attr(COMMON.HREF.value)
        }
        return Author(value, href)
    }

    private fun parseDate(element: Element): Date {
        val format = SimpleDateFormat(FOOTER.TIMESTAMP_FORMAT.value, Locale.ENGLISH)
        val timestamp = element.select(FOOTER.TIMESTAMP.value).text()
        return format.parse(timestamp)
    }
}