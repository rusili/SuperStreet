package com.rusili.superstreet.ui.list.rv

import android.view.View
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import com.rusili.superstreet.domain.models.header.Title
import com.rusili.superstreet.ui.common.BaseViewHolder
import com.rusili.superstreet.ui.util.DateHelper
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.preview_viewholder.*

class PreviewViewHolder(override val containerView: View,
                        private val onClick: (Title) -> Unit,
                        private val glide: RequestManager)
    : BaseViewHolder<ArticlePreviewModel>(containerView), LayoutContainer {

    override fun bind(preview: ArticlePreviewModel) {
        val requestOptions = RequestOptions().placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.ic_error_outline_black_24dp)
                .transforms(CenterCrop(), RoundedCorners(12))

        glide.load(preview.header.headerImage.resizeToDefaultSize())
                .apply(requestOptions)
                .into(previewThumbnail)

        previewTitle.text = preview.header.title.value
        previewDesc.text = preview.header.desc
        previewMag.text = preview.flag.magazine.value
        previewType.text = preview.flag.type.value
        previewAuthorTimestamp.text = preview.footer.author.value + " " + DateHelper.getDateDifferenceString(preview.footer.date)

        previewTitle.setOnClickListener { onClick(preview.header.title) }
    }
}