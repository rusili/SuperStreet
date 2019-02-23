package com.rusili.superstreet.article.ui.rv

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.ImageGroup
import com.rusili.superstreet.common.models.body.ImageSize
import com.rusili.superstreet.common.ui.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.article_imagegroup_viewholder.*

class ImageGroupViewHolder(
    override val containerView: View,
    val onClick: (View, Image, ImageSize) -> Unit,
    val glide: RequestManager
) : BaseViewHolder<ImageGroup>(containerView), LayoutContainer {

    override fun bind(model: ImageGroup) {
        loadImage(model.imageList[0], imageGroupImage1)
        loadImage(model.imageList[1], imageGroupImage2)
        loadImage(model.imageList[2], imageGroupImage3)
    }

    private fun loadImage(
        image: Image,
        view: ImageView
    ) {
        glide.load(image.resizeToGroupSize())
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA))
            .into(view)

        view.setOnClickListener { onClick(it, image, ImageSize.GROUP) }
    }
}
