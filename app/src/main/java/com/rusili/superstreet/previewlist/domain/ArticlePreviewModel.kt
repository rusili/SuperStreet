package com.rusili.superstreet.previewlist.domain

import com.rusili.superstreet.common.models.Flag
import com.rusili.superstreet.common.models.Footer
import com.rusili.superstreet.common.models.Header

data class ArticlePreviewModel(
    val flag: Flag,
    val header: Header,
    val footer: Footer,
    val size: CardSize
) : Comparable<ArticlePreviewModel> {

    fun getViewType() =
        when (size) {
            CardSize.Large -> 1
            CardSize.Small -> 0
        }

    override fun compareTo(other: ArticlePreviewModel) =
        when {
            size.viewType < other.size.viewType -> -1
            size.viewType > other.size.viewType -> 1
            else -> 0
        }
}
