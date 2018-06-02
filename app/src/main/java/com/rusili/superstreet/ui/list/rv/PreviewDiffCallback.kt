package com.rusili.superstreet.ui.list.rv

import android.support.v7.util.DiffUtil
import com.rusili.superstreet.domain.list.ArticlePreviewModel

class PreviewDiffCallback
    : DiffUtil.ItemCallback<ArticlePreviewModel>(){

    override fun areItemsTheSame(oldItem: ArticlePreviewModel,
                                 newItem: ArticlePreviewModel): Boolean {
        return oldItem.header.title == newItem.header.title
    }

    override fun areContentsTheSame(oldItem: ArticlePreviewModel,
                                    newItem: ArticlePreviewModel): Boolean {
        return oldItem == newItem
    }
}