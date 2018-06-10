package com.rusili.superstreet.ui.article.rv

import android.view.View
import com.rusili.superstreet.domain.models.body.Paragraph
import com.rusili.superstreet.ui.common.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.article_paragraph_viewholder.*

class ParagraphViewHolder(override val containerView: View)
    : BaseViewHolder<Paragraph>(containerView), LayoutContainer {

    override fun bind(model: Paragraph) {
        articleParagraph.text = model.body
    }
}