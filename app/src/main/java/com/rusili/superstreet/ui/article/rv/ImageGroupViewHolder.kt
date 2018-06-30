package com.rusili.superstreet.ui.article.rv

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.domain.models.body.ImageGroup
import com.rusili.superstreet.ui.common.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.article_imagegroup_viewholder.*

class ImageGroupViewHolder(override val containerView: View)
    : BaseViewHolder<ImageGroup>(containerView), LayoutContainer {

    override fun bind(model: ImageGroup) {
        Glide.with(containerView)
                .load(model.imageList[0].resizeToGroupSize())
                .apply(RequestOptions().transforms(CenterCrop()))
                .into(imageGroupImage1)

        Glide.with(containerView)
                .load(model.imageList[1].resizeToGroupSize())
                .apply(RequestOptions().transforms(CenterCrop()))
                .into(imageGroupImage2)

        Glide.with(containerView)
                .load(model.imageList[2].resizeToGroupSize())
                .apply(RequestOptions().transforms(CenterCrop()))
                .into(imageGroupImage3)

        // TODO: Add clicklistener to view enlarged headerImage
    }
}