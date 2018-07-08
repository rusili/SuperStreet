package com.rusili.superstreet.ui.common

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.models.body.AbstractBodyModel
import com.rusili.superstreet.domain.models.body.ImageGallery
import com.rusili.superstreet.domain.models.body.ImageGroup
import com.rusili.superstreet.ui.common.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.article_imagegroup_viewholder.*

abstract class BaseImageViewHolder<T>(override val containerView: View,
                                      open val onClick: (View, String) -> Unit,
                                      open val glide: RequestManager)
    : BaseViewHolder<T>(containerView), LayoutContainer {

    private val requestOptions = RequestOptions().placeholder(R.drawable.bg_placeholder)
            .error(R.drawable.ic_error_outline_black_24dp)
            .transforms(CenterCrop())

    open fun loadImage(image: ImageGallery,
                          view: ImageView) {
        glide.load(image.resizeToGroupSize())
                .apply(requestOptions)
                .into(view)
        view.setOnClickListener { onClick(it, image.resizeTo1920By1280()) }
    }
}