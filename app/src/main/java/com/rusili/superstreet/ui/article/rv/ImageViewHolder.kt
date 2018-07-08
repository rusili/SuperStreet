package com.rusili.superstreet.ui.article.rv

import android.view.View
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.models.body.ImageGallery
import com.rusili.superstreet.ui.common.BaseImageViewHolder
import com.rusili.superstreet.ui.common.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.article_image_viewholder.*

class ImageViewHolder(override val containerView: View,
                      override val onClick: (View, String) -> Unit,
                      override val glide: RequestManager)
    : BaseImageViewHolder<ImageGallery>(containerView, onClick, glide), LayoutContainer {

    override fun bind(model: ImageGallery) {
        loadImage(model, articleImageView)
    }
}