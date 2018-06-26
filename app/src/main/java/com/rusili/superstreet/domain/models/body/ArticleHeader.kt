package com.rusili.superstreet.domain.models.body

import com.rusili.superstreet.domain.models.Flag
import com.rusili.superstreet.domain.models.Footer
import com.rusili.superstreet.domain.models.Header

data class ArticleHeader(override val id: Int,
                         val header: Header,
                         val footer: Footer,
                         val flag: Flag) : AbstractBodyModel(id) {

    override fun getViewType() = ArticleViewType.HEADER.viewType
}