package com.rusili.superstreet.ui.article.rv

import android.view.View
import com.rusili.superstreet.domain.models.body.Paragraph
import com.rusili.superstreet.ui.common.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer

class ParagraphViewHolder(override val containerView: View)
    : BaseViewHolder<Paragraph>(containerView), LayoutContainer {

    override fun bind(model: Paragraph) {
    }
}