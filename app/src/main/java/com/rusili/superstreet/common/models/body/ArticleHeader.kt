package com.rusili.superstreet.common.models.body

import com.rusili.superstreet.common.models.Flag
import com.rusili.superstreet.common.models.Footer
import com.rusili.superstreet.common.models.Header

data class ArticleHeader(
    override val id: Int,
    val header: Header,
    val footer: Footer,
    val flag: Flag
) : AbstractBodyModel(id) {

    override fun getViewType() = ArticleViewType.HEADER.viewType
}
