package com.rusili.superstreet.article.ui.rv

import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.rusili.superstreet.R
import com.rusili.superstreet.common.extensions.fadeAndHide
import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.ImageSize
import com.rusili.superstreet.common.ui.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.article_image_viewholder.*
import timber.log.Timber

class ImageViewHolder(
    override val containerView: View,
    val onClick: (View, Image, ImageSize) -> Unit,
    val glide: RequestManager
) : BaseViewHolder<Image>(containerView), LayoutContainer {

    override fun bind(image: Image) {
        val listener = object : RequestListener<Drawable> {
            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                articleImageLoadingLayout.fadeAndHide()
                articleImageView.isVisible = true
                return false
            }

            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                Timber.e(e, "Error loading full image: %s", image.resizeToDefaultSize())
                articleImageLoadingLayout.fadeAndHide()
                articleImageView.isVisible = true
                return true
            }
        }

        ViewCompat.setTransitionName(articleImageView, image.id.toString())

        glide.load(image.resizeToDefaultSize())
            .apply(RequestOptions().dontTransform())
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(listener)
            .into(articleImageView)

        articleImageView.setOnClickListener { onClick(it, image, ImageSize.DEFAULT) }
    }
}
