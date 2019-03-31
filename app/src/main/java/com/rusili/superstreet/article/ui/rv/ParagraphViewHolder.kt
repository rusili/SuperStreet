package com.rusili.superstreet.article.ui.rv

import android.view.View
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import com.rusili.superstreet.common.base.BaseViewHolder
import com.rusili.superstreet.common.models.body.Paragraph
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewholder_article_paragraph.*

class ParagraphViewHolder(override val containerView: View) :
    BaseViewHolder<Paragraph>(containerView), LayoutContainer {

    override fun bind(
        model: Paragraph,
        position: Int
    ) {
        val textFuture = PrecomputedTextCompat.getTextFuture(
            model.body,
            TextViewCompat.getTextMetricsParams(articleParagraph),
            null
        )

        articleParagraph.setTextFuture(textFuture)
    }
}
