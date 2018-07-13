package com.rusili.superstreet.domain.list

import androidx.paging.PageKeyedDataSource
import io.reactivex.disposables.CompositeDisposable

private const val PAGE_PATH = "page-"
private const val BACKSLASH = "/"

class ArticleListDataSource(private val articleListRepository: ArticleListRepository)
    : PageKeyedDataSource<Int, ArticlePreviewModel>() {

    override fun loadInitial(params: LoadInitialParams<Int>,
                             callback: LoadInitialCallback<Int, ArticlePreviewModel>) =
            callback.onResult(articleListRepository.getArticleStream(), 1, 2)

    override fun loadAfter(params: LoadParams<Int>,
                           callback: LoadCallback<Int, ArticlePreviewModel>) =
            callback.onResult(articleListRepository.getArticleStream(pageToPath(params.key)), params.key + 1)

    override fun loadBefore(params: LoadParams<Int>,
                            callback: LoadCallback<Int, ArticlePreviewModel>) {
    }

    fun pageToPath(page: Int) =
            PAGE_PATH + page + BACKSLASH
}