package com.rusili.superstreet.ui.article.rv

import android.view.View
import com.bumptech.glide.RequestManager
import com.rusili.superstreet.domain.models.body.ImageGallery
import com.rusili.superstreet.ui.common.BaseImageViewHolder
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