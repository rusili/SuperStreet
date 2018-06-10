package com.rusili.superstreet.ui.article.rv

import androidx.recyclerview.widget.DiffUtil
import com.rusili.superstreet.domain.article.ArticleFullModel

class ArticleDiffCallback
    : DiffUtil.ItemCallback<ArticleFullModel>() {

    override fun areItemsTheSame(oldItem: ArticleFullModel,
                                 newItem: ArticleFullModel): Boolean {
        return oldItem.header.title == newItem.header.title
    }

    override fun areContentsTheSame(oldItem: ArticleFullModel,
                                    newItem: ArticleFullModel): Boolean {
        return oldItem == newItem
    }
}