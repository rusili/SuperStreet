package com.rusili.superstreet.ui.article

import com.rusili.superstreet.ui.common.BaseViewModel

class ArticleViewModel
    : BaseViewModel(), ArticleContract.Presenter {
    private var view: ArticleContract.View? = null

    override fun start(view: ArticleContract.View) {
        this.view = view
    }

    override fun stop() {
        view = null
        onCleared()
    }
}