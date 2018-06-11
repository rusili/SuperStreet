package com.rusili.superstreet.ui.list.rv

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import com.rusili.superstreet.domain.models.header.Title
import com.rusili.superstreet.ui.common.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.preview_viewholder.*

class PreviewViewHolder(override val containerView: View,
                        private val onClick: (Title) -> Unit)
    : BaseViewHolder<ArticlePreviewModel>(containerView), LayoutContainer {

    override fun bind(preview: ArticlePreviewModel) {
        Glide.with(containerView)
                .load(preview.header.image.src)
                .apply(RequestOptions().transforms(CenterCrop(), RoundedCorners(12)))
                .into(previewThumbnail)

        previewTitle.text = preview.header.title.value
        previewDesc.text = preview.header.desc
        previewMag.text = preview.flag.magazine.value
        previewType.text = preview.flag.type.value
        previewAuthorTimestamp.text = preview.footer.author.value + " " + preview.footer.author.href + " " + preview.footer.date.toLocaleString()

        previewTitle.setOnClickListener { onClick(preview.header.title) }
    }
}