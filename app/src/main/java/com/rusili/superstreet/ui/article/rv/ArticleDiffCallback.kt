package com.rusili.superstreet.ui.article.rv

import androidx.recyclerview.widget.DiffUtil
import com.rusili.superstreet.domain.models.body.AbstractBodyModel

class ArticleDiffCallback
    : DiffUtil.ItemCallback<AbstractBodyModel>() {

    override fun areItemsTheSame(oldItem: AbstractBodyModel,
                                 newItem: AbstractBodyModel) =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: AbstractBodyModel,
                                    newItem: AbstractBodyModel) =
            oldItem == newItem
}