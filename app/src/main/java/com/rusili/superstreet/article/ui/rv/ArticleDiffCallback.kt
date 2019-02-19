package com.rusili.superstreet.article.ui.rv

import androidx.recyclerview.widget.DiffUtil
import com.rusili.superstreet.common.models.body.AbstractBodyModel

class ArticleDiffCallback : DiffUtil.ItemCallback<AbstractBodyModel>() {

    override fun areItemsTheSame(
        oldItem: AbstractBodyModel,
        newItem: AbstractBodyModel
    ) =
        oldItem == newItem

    override fun areContentsTheSame(
        oldItem: AbstractBodyModel,
        newItem: AbstractBodyModel
    ) =
        oldItem.id == newItem.id
}
