package com.rusili.superstreet.ui.article.rv

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.rusili.superstreet.domain.models.body.ImageGallery
import com.rusili.superstreet.domain.models.body.ImageGroup
import com.rusili.superstreet.ui.common.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.article_image_viewholder.*

class ImageViewHolder(override val containerView: View,
                      val onClick: (View, String) -> Unit,
                      val glide: RequestManager)
    : BaseViewHolder<ImageGallery>(containerView), LayoutContainer {

    override fun bind(model: ImageGallery) {
        loadImage(model, articleImageView)
    }

    private fun loadImage(image: ImageGallery,
                          view: ImageView) {
        glide.load(image.resizeToDefaultSize())
                .into(view)
        view.setOnClickListener { onClick(it, image.resizeTo1920By1280()) }
    }
}