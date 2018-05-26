package com.rusili.superstreet.ui.list.rv

import android.support.v7.util.DiffUtil
import com.rusili.superstreet.domain.list.model.ArticlePreview

class PreviewDiffCallback
    : DiffUtil.ItemCallback<ArticlePreview>(){
    override fun areItemsTheSame(oldItem: ArticlePreview?,
                                 newItem: ArticlePreview?): Boolean {
        return oldItem?.header?.title == newItem?.header?.title
    }

    override fun areContentsTheSame(oldItem: ArticlePreview?,
                                    newItem: ArticlePreview?): Boolean {
        return oldItem == newItem
    }
}