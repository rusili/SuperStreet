package com.rusili.superstreet.previewlist.ui.rv

import android.view.View
import androidx.core.view.ViewCompat
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rusili.superstreet.common.base.BaseViewHolder
import com.rusili.superstreet.common.models.Header
import com.rusili.superstreet.previewlist.DateHelper
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewholder_preview_large.*
import java.util.Date

private val crossFadeTransition = DrawableTransitionOptions.withCrossFade()

class PreviewViewHolder(
    override val containerView: View,
    private val onClick: (View, Header) -> Unit,
    private val glide: RequestManager,
    private val dateHelper: DateHelper
) : BaseViewHolder<ArticlePreviewModel>(containerView), LayoutContainer {

    override fun bind(preview: ArticlePreviewModel) {
        ViewCompat.setTransitionName(previewThumbnail, preview.header.headerImage.title)
        ViewCompat.setTransitionName(previewLayout, preview.header.headerImage.resizeToDefaultSize())

        glide.load(preview.header.headerImage.resizeToDefaultSize())
            .transition(crossFadeTransition)
            .into(previewThumbnail)

        previewTitle.text = preview.header.title.value
//        previewDesc.text = preview.header.desc
        previewMag.text = preview.flag.magazine.value
        previewType.text = preview.flag.type.value
        previewAuthorTimestamp.text = dateHelper.getDateDifferenceString(Date(), preview.footer.date) + " - " + preview.footer.author.value

        containerView.setOnClickListener { onClick(previewThumbnail, preview.header) }
    }
}
