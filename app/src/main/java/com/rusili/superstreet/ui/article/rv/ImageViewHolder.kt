package com.rusili.superstreet.ui.article.rv

import android.view.View
import com.rusili.superstreet.domain.models.body.ImageGallery
import com.rusili.superstreet.ui.common.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer

class ImageViewHolder(override val containerView: View)
    : BaseViewHolder<ImageGallery>(containerView), LayoutContainer {

    override fun bind(model: ImageGallery) {
    }
}