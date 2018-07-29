package com.rusili.superstreet.ui.article.rv

import android.media.Image
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.domain.models.body.ImageGallery
import com.rusili.superstreet.domain.models.body.ImageGroup
import com.rusili.superstreet.domain.models.body.ImageSize
import com.rusili.superstreet.ui.common.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.article_image_viewholder.*

class ImageViewHolder(override val containerView: View,
                      val onClick: (View, ImageGallery, ImageSize) -> Unit,
                      val glide: RequestManager)
    : BaseViewHolder<ImageGallery>(containerView), LayoutContainer {

    override fun bind(model: ImageGallery) {
        loadImage(model, articleImageView)
    }

    private fun loadImage(image: ImageGallery,
                          view: ImageView) {
        glide.load(image.resizeToDefaultSize())
                .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA))
                .into(view)
        view.setOnClickListener { onClick(it, image, ImageSize.DEFAULT) }
    }
}