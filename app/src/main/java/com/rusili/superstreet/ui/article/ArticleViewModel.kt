package com.rusili.superstreet.ui.article

import com.rusili.superstreet.ui.common.BaseViewModel
import com.rusili.superstreet.ui.list.ArticleListContract

class ArticleViewModel : BaseViewModel(), ArticleContract.Presenter {
    private var view: ArticleContract.View? = null

    override fun start(view: ArticleContract.View) {
        this.view = view
    }

    override fun stop() {
        view = null
        onCleared()
    }
}