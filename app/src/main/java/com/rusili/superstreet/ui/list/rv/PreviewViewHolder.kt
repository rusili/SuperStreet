package com.rusili.superstreet.ui.list.rv

import android.support.v7.widget.RecyclerView
import android.view.View
import com.rusili.superstreet.domain.list.model.ArticlePreview
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.preview_viewholder.*

class PreviewViewHolder(override val containerView: View)
    : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(preview: ArticlePreview) {
        previewTitle.text = preview.header.title.value
    }
}