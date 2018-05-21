package com.rusili.superstreet.ui.list

import com.rusili.superstreet.domain.usecase.ArticleListUsecase

interface ArticleListContract {

    interface View {
        fun goToClickedArticle()
        fun showPreviewArticles(test: String)
    }

    interface Presenter {
        fun start(view: View)
        fun stop()
    }
}