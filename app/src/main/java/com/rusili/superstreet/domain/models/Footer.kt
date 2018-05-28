package com.rusili.superstreet.domain.models

import com.rusili.superstreet.domain.models.footer.Author
import java.util.*

data class Footer(val author: Author,
                  val date: Date)