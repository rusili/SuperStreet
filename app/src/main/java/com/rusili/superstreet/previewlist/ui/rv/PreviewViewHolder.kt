package com.rusili.superstreet.previewlist.ui.rv

import android.view.View
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.common.models.header.Title
import com.rusili.superstreet.common.ui.BaseViewHolder
import com.rusili.superstreet.previewlist.DateHelper
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.preview_viewholder_large.*
import java.util.*

class PreviewViewHolder(
    override val containerView: View,
    private val onClick: (View, Title) -> Unit,
    private val glide: RequestManager,
    private val dateHelper: DateHelper
) : BaseViewHolder<ArticlePreviewModel>(containerView), LayoutContainer {

    override fun bind(preview: ArticlePreviewModel) {
        val requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(12))

        glide.load(preview.header.headerImage.resizeToDefaultSize())
            .apply(requestOptions)
            .into(previewThumbnail)

        val dateString = dateHelper.getDateDifferenceString(Date(), preview.footer.date)
        previewTitle.text = preview.header.title.value
        previewDesc.text = preview.header.desc
        previewMag.text = preview.flag.magazine.value
        previewType.text = preview.flag.type.value
        previewAuthorTimestamp.text = dateString

        previewTitle.setOnClickListener { onClick(containerView, preview.header.title) }
        previewThumbnail.setOnClickListener { onClick(containerView, preview.header.title) }
    }
}
