package com.rusili.superstreet.ui.article.rv

import android.view.View
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.models.body.ImageGallery
import com.rusili.superstreet.ui.common.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.article_image_viewholder.*

class ImageViewHolder(override val containerView: View,
                      private val onClick: (View, String) -> Unit,
                      private val glide: RequestManager)
    : BaseViewHolder<ImageGallery>(containerView), LayoutContainer {

    override fun bind(model: ImageGallery) {
        val requestOptions = RequestOptions().placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.ic_error_outline_black_24dp)
                .transforms(CenterCrop(), RoundedCorners(12))

        glide.load(model.resizeToDefaultSize())
                .apply(requestOptions)
                .into(articleImageView)

        containerView.setOnClickListener { onClick(containerView, model.resizeTo1920By1280()) }
    }
}