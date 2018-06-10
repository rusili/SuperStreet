package com.rusili.superstreet.domain.article

import com.rusili.superstreet.domain.models.ArticleHeader
import com.rusili.superstreet.domain.models.Body
import com.rusili.superstreet.domain.models.Flag
import com.rusili.superstreet.domain.models.Footer

data class ArticleFullModel(val flag: Flag,
                            val header: ArticleHeader,
                            val body: Body,
                            val footer: Footer)