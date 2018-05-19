package com.rusili.superstreet.ui.article

interface ArticleContract {

    interface View {
        fun goBackToList()
    }

    interface Presenter {

        fun start(view : View)
        fun stop()
    }
}