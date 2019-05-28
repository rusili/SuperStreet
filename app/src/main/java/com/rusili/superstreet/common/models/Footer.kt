package com.rusili.superstreet.common.models

import com.rusili.superstreet.common.extensions.isPositive
import com.rusili.superstreet.common.models.footer.Author
import java.util.Date

data class Footer(
    val author: Author,
    val date: Date
) {

    fun isValid() =
        author.isValid() && date.time.isPositive()
}
