package com.rusili.superstreet.article.ui.rv

import android.view.View
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.common.base.BaseViewHolder
import com.rusili.superstreet.common.extensions.fadeAndHide
import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.ImageGroup
import com.rusili.superstreet.common.models.body.ImageSize
import com.rusili.superstreet.common.ui.SimpleRequestListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewholder_article_image.*
import kotlinx.android.synthetic.main.viewholder_article_imagegroup.*

private val glideOptions = RequestOptions().dontTransform()
private val crossFadeTransition = DrawableTransitionOptions.withCrossFade()

class ImageGroupViewHolder(
    override val containerView: View,
    private val onClick: (View, Image, ImageSize) -> Unit,
    private val glide: RequestManager
) : BaseViewHolder<ImageGroup>(containerView), LayoutContainer {
    private val imageGroup = listOf<ImageView>(imageGroupImage1, imageGroupImage2, imageGroupImage3)

    override fun bind(model: ImageGroup) {
        model.imageList
            .take(3)
            .forEachIndexed { index, image ->
                imageGroup[index].isVisible = true
                loadImage(model.imageList[index], imageGroup[index])
            }
    }

    private fun loadImage(
        image: Image,
        view: ImageView
    ) {
        ViewCompat.setTransitionName(view, image.id.toString())

        glide.load(image.resizeToGroupSize())
            .apply(glideOptions)
            .transition(crossFadeTransition)
            .into(view)

        view.setOnClickListener { onClick(it, image, ImageSize.GROUP) }
    }
}
