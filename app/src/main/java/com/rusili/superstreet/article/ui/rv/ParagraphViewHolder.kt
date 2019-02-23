package com.rusili.superstreet.article.ui.rv

import android.view.View
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import com.rusili.superstreet.common.models.body.Paragraph
import com.rusili.superstreet.common.ui.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.article_paragraph_viewholder.*

class ParagraphViewHolder(override val containerView: View) : BaseViewHolder<Paragraph>(containerView), LayoutContainer {

    override fun bind(model: Paragraph) {
        val textFuture = PrecomputedTextCompat
            .getTextFuture(model.body, TextViewCompat.getTextMetricsParams(articleParagraph), null)

        articleParagraph.setTextFuture(textFuture)
    }
}
