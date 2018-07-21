package com.rusili.superstreet.ui.common

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.models.body.ImageGallery
import kotlinx.android.extensions.LayoutContainer

abstract class BaseImageViewHolder<T>(override val containerView: View,
                                      open val onClick: (View, String) -> Unit,
                                      open val glide: RequestManager)
    : BaseViewHolder<T>(containerView), LayoutContainer {

    open fun loadImage(image: ImageGallery,
                       view: ImageView) {
        glide.load(image.resizeToGroupSize())
                .into(view)
        view.setOnClickListener { onClick(it, image.resizeTo1920By1280()) }
    }
}