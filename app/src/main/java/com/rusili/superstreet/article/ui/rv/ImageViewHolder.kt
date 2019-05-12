package com.rusili.superstreet.article.ui.rv

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import com.bumptech.glide.RequestManager
import com.rusili.superstreet.common.base.BaseViewHolder
import com.rusili.superstreet.common.extensions.fadeAndHide
import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.Image.Companion.IMAGE_DEFAULT_HEIGHT
import com.rusili.superstreet.common.models.body.Image.Companion.IMAGE_DEFAULT_WIDTH
import com.rusili.superstreet.common.models.body.ImageSize
import com.rusili.superstreet.common.ui.SimpleRequestListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewholder_article_image.*

class ImageViewHolder(
    override val containerView: View,
    private val onClick: (View, Image, ImageSize) -> Unit,
    private val glide: RequestManager
) : BaseViewHolder<Image>(containerView), LayoutContainer {
    private val glideListener = object : SimpleRequestListener() {
        override fun onReadyOrFailed() {
            articleImageLoadingLayout.fadeAndHide()
            articleImageView.isVisible = true
        }
    }

    override fun bind(model: Image) {
        ViewCompat.setTransitionName(articleImageView, model.id.toString())

        glide.load(model.getDefaultSizeUrl())
            .listener(glideListener)
            .override(IMAGE_DEFAULT_WIDTH, IMAGE_DEFAULT_HEIGHT)
            .into(articleImageView)

        articleImageView.setOnClickListener { onClick(it, model, ImageSize.DEFAULT) }
    }
}
