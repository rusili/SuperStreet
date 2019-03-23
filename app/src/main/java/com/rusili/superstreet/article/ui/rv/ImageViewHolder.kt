package com.rusili.superstreet.article.ui.rv

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.common.base.BaseViewHolder
import com.rusili.superstreet.common.extensions.fadeAndHide
import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.ImageSize
import com.rusili.superstreet.common.ui.SimpleRequestListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewholder_article_image.*

class ImageViewHolder(
    override val containerView: View,
    private val onClick: (View, Image, ImageSize) -> Unit,
    private val glide: RequestManager
) : BaseViewHolder<Image>(containerView), LayoutContainer {
    private val glideOptions = RequestOptions().dontTransform().centerCrop()
    private val glideListener = object : SimpleRequestListener() {
        override fun onReadyOrFailed() {
            articleImageLoadingLayout.fadeAndHide()
            articleImageView.isVisible = true
        }
    }

    override fun bind(model: Image) {
        ViewCompat.setTransitionName(articleImageView, model.id.toString())

        glide.load(model.resizeToDefaultSize())
            .apply(glideOptions)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(glideListener)
            .into(articleImageView)

        articleImageView.setOnClickListener { onClick(it, model, ImageSize.DEFAULT) }
    }
}
