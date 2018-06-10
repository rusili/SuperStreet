package com.rusili.superstreet.ui.article.rv

import androidx.recyclerview.widget.DiffUtil
import com.rusili.superstreet.domain.models.body.BaseBody2

class ArticleDiffCallback
    : DiffUtil.ItemCallback<BaseBody2>() {

    override fun areItemsTheSame(oldItem: BaseBody2,
                                 newItem: BaseBody2) =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BaseBody2, newItem: BaseBody2) =
            oldItem == newItem
}