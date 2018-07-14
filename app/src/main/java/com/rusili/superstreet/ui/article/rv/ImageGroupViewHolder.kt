package com.rusili.superstreet.ui.article.rv

import android.view.View
import com.bumptech.glide.RequestManager
import com.rusili.superstreet.domain.models.body.ImageGroup
import com.rusili.superstreet.ui.common.BaseImageViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.article_imagegroup_viewholder.*

class ImageGroupViewHolder(override val containerView: View,
                           override val onClick: (View, String) -> Unit,
                           override val glide: RequestManager)
    : BaseImageViewHolder<ImageGroup>(containerView, onClick, glide), LayoutContainer {

    override fun bind(model: ImageGroup) {
        loadImage(model.imageList[0], imageGroupImage1)
        loadImage(model.imageList[1], imageGroupImage2)
        loadImage(model.imageList[2], imageGroupImage3)
    }
}