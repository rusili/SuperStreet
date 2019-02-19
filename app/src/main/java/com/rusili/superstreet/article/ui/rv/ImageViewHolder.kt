package com.rusili.superstreet.article.ui.rv

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.common.models.body.ImageGallery
import com.rusili.superstreet.common.models.body.ImageSize
import com.rusili.superstreet.common.ui.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.article_image_viewholder.*

class ImageViewHolder(
    override val containerView: View,
    val onClick: (View, ImageGallery, ImageSize) -> Unit,
    val glide: RequestManager
) : BaseViewHolder<ImageGallery>(containerView), LayoutContainer {

    override fun bind(model: ImageGallery) {
        glide.load(model.resizeToDefaultSize())
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA))
            .into(articleImageView)

        articleImageView.setOnClickListener { onClick(it, model, ImageSize.DEFAULT) }
    }
}
