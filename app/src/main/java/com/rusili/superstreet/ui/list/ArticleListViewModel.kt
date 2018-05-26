package com.rusili.superstreet.ui.list

import com.rusili.superstreet.domain.list.ArticleListUsecase
import com.rusili.superstreet.ui.common.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticleListViewModel(private val usecase: ArticleListUsecase)
    : BaseViewModel(), ArticleListContract.Presenter {
    private var view: ArticleListContract.View? = null

    override fun start(view: ArticleListContract.View) {
        this.view = view
        getPreviewArticles()
    }

    private fun getPreviewArticles() {
        usecase.getArticleStream()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { list ->
                    view?.showPreviewArticles(list)
                }
    }

    override fun stop() {
        view = null
        onCleared()
    }
}