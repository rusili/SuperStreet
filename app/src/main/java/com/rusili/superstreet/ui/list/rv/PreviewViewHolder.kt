package com.rusili.superstreet.ui.list.rv

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import com.rusili.superstreet.domain.models.header.Title
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.preview_viewholder.*

class PreviewViewHolder(override val containerView: View,
                        private val onClick: (Title) -> Unit)
    : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(preview: ArticlePreviewModel) {
        Glide.with(containerView.context)
                .load(preview.header.image.src)
                .placeholder(R.drawable.ic_launcher_background)
                .into(previewThumbnail)

        previewTitle.text = preview.header.title.value
        previewTitleLink.text = preview.header.title.href
        previewDesc.text = preview.header.desc
        previewMag.text = preview.flag.magazine.value + " " + preview.flag.magazine.href
        previewType.text = preview.flag.type.value + " " + preview.flag.type.href
        previewAuthorTimestamp.text = preview.footer.author.value + " " + preview.footer.author.href + " " + preview.footer.date.toLocaleString()

        previewTitle.setOnClickListener { onClick(preview.header.title) }
    }
}