package com.rusili.superstreet.article.domain

import com.rusili.superstreet.common.models.Body
import com.rusili.superstreet.common.models.Flag
import com.rusili.superstreet.common.models.Footer
import com.rusili.superstreet.common.models.Header

data class ArticleFullModel(
    val flag: Flag,
    val header: Header,
    val body: Body,
    val footer: Footer
)
