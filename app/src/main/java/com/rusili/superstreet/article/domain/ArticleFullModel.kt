package com.rusili.superstreet.article.domain

import com.rusili.superstreet.common.models.BaseArticleModel
import com.rusili.superstreet.common.models.Body
import com.rusili.superstreet.common.models.Flag
import com.rusili.superstreet.common.models.Footer
import com.rusili.superstreet.common.models.Header

data class ArticleFullModel(
    override val flag: Flag,
    override val header: Header,
    val body: Body,
    override val footer: Footer
) : BaseArticleModel()
