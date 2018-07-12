package com.rusili.superstreet.domain.list

import androidx.paging.PageKeyedDataSource
import io.reactivex.disposables.CompositeDisposable

class ArticleListDataSource(private val disposable: CompositeDisposable,
                            private val articleListRepository: ArticleListRepository,
                            private val searchTerm: String)
    : PageKeyedDataSource<String, ArticlePreviewModel>() {

    override fun loadInitial(params: LoadInitialParams<String>,
                             callback: LoadInitialCallback<String, ArticlePreviewModel>) {
        params.
    }

    override fun loadAfter(params: LoadParams<String>,
                           callback: LoadCallback<String, ArticlePreviewModel>) {
    }

    override fun loadBefore(params: LoadParams<String>,
                            callback: LoadCallback<String, ArticlePreviewModel>) {
    }

}