package com.rusili.superstreet.domain.article

import com.rusili.superstreet.domain.models.ArticleHeader
import com.rusili.superstreet.domain.models.Flag
import com.rusili.superstreet.domain.models.Footer
import com.rusili.superstreet.domain.models.Header

data class ArticleFullModel(val flag: Flag,
                            val header: ArticleHeader,
                            val footer: Footer)