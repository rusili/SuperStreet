package com.rusili.superstreet.previewlist.ui.rv

import androidx.recyclerview.widget.DiffUtil
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel

class PreviewDiffCallback
    : DiffUtil.ItemCallback<ArticlePreviewModel>() {

    override fun areItemsTheSame(oldItem: ArticlePreviewModel,
                                 newItem: ArticlePreviewModel
    ): Boolean =
            oldItem.header.title == newItem.header.title

    override fun areContentsTheSame(oldItem: ArticlePreviewModel,
                                    newItem: ArticlePreviewModel
    ): Boolean =
            oldItem == newItem
}