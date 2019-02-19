package com.rusili.superstreet.previewlist.domain

import androidx.room.Entity
import com.rusili.superstreet.common.models.Flag
import com.rusili.superstreet.common.models.Footer
import com.rusili.superstreet.common.models.Header

@Entity(tableName = "articlePreviews")
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
            size < other.size -> -1
            size > other.size -> 1
            else -> 0
        }
}