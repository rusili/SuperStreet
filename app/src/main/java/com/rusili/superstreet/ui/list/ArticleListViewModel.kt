package com.rusili.superstreet.ui.list

import com.rusili.superstreet.ui.common.BaseViewModel

class ArticleListViewModel : BaseViewModel(), ArticleListContract.Presenter {
    private var view: ArticleListContract.View? = null

    override fun start(view: ArticleListContract.View) {
        this.view = view
    }

    override fun stop() {
        view = null
        onCleared()
    }
}