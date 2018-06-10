package com.rusili.superstreet.ui.article.rv

import android.view.View
import com.rusili.superstreet.domain.models.body.ImageGroup
import com.rusili.superstreet.ui.common.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer

class ImageGroupViewHolder(override val containerView: View)
    : BaseViewHolder<ImageGroup>(containerView), LayoutContainer {

    override fun bind(model: ImageGroup) {
    }
}