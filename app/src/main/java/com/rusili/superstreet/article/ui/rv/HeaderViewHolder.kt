package com.rusili.superstreet.article.ui.rv

import android.view.View
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.common.base.BaseViewHolder
import com.rusili.superstreet.common.models.body.ArticleHeader
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.article_header_viewholder.*

class HeaderViewHolder(
    override val containerView: View,
    private val glide: RequestManager
) : BaseViewHolder<ArticleHeader>(containerView), LayoutContainer {
    val requestOptions = RequestOptions().dontTransform().centerCrop()

    override fun bind(model: ArticleHeader) {
        glide.load(model.header.headerImage.resizeToDefaultSize())
            .apply(requestOptions)
            .into(articleHeaderImageView)

        articleHeaderTitle.text = model.header.title.value
    }
}
