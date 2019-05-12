package com.rusili.superstreet.article.ui.rv

import android.view.View
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import com.bumptech.glide.RequestManager
import com.rusili.superstreet.common.base.BaseViewHolder
import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.ImageGroup
import com.rusili.superstreet.common.models.body.ImageSize
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewholder_article_imagegroup.*

private const val MAX_NUM_OF_IMAGES = 3

class ImageGroupViewHolder(
    override val containerView: View,
    private val onClick: (View, Image, ImageSize) -> Unit,
    private val glide: RequestManager
) : BaseViewHolder<ImageGroup>(containerView), LayoutContainer {
    private val imageGroup = listOf<ImageView>(imageGroupImage1, imageGroupImage2, imageGroupImage3)

    override fun bind(model: ImageGroup) {
        model.imageList
            .take(MAX_NUM_OF_IMAGES)
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
            .into(view)

        view.setOnClickListener { onClick(it, image, ImageSize.GROUP) }
    }
}
