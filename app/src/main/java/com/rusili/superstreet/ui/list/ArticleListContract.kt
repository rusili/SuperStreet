package com.rusili.superstreet.ui.list

interface ArticleListContract {

    interface View {
        fun goToClickedArticle()
    }

    interface Presenter {

        fun start(view: View)
        fun stop()
    }
}