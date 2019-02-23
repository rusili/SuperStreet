package com.rusili.superstreet.article.ui.rv

import android.view.View
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.common.models.body.ArticleHeader
import com.rusili.superstreet.common.ui.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.article_header_viewholder.*

class HeaderViewHolder(
    override val containerView: View,
    private val glide: RequestManager
) : BaseViewHolder<ArticleHeader>(containerView), LayoutContainer {

    override fun bind(model: ArticleHeader) {
        val requestOptions = RequestOptions().transforms(CenterCrop())

        glide.load(model.header.headerImage.resizeToDefaultSize())
            .apply(requestOptions)
            .into(articleHeaderImageView)

        articleHeaderTitle.text = model.header.title.value
    }
}
