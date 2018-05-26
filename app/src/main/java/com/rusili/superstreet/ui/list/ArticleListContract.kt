package com.rusili.superstreet.ui.list

import com.rusili.superstreet.domain.list.ArticlePreviewModel

interface ArticleListContract {

    interface View {
        fun goToClickedArticle()
        fun showPreviewArticles(list: List<ArticlePreviewModel>)
    }

    interface Presenter {
        fun start(view: View)
        fun stop()
    }
}