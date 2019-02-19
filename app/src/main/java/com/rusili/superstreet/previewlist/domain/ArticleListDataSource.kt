package com.rusili.superstreet.previewlist.domain

import androidx.paging.PageKeyedDataSource

private const val PAGE_PATH = "page-"
private const val BACKSLASH = "/"

class ArticleListDataSource(private val previewListRepository: PreviewListRepository)
    : PageKeyedDataSource<Int, ArticlePreviewModel>() {

    override fun loadInitial(params: LoadInitialParams<Int>,
                             callback: LoadInitialCallback<Int, ArticlePreviewModel>) =
            callback.onResult(previewListRepository.getArticleStream(), 1, 2)

    override fun loadAfter(params: LoadParams<Int>,
                           callback: LoadCallback<Int, ArticlePreviewModel>) =
            callback.onResult(previewListRepository.getArticleStream(pageToPath(params.key)), params.key + 1)

    override fun loadBefore(params: LoadParams<Int>,
                            callback: LoadCallback<Int, ArticlePreviewModel>) = Unit

    private fun pageToPath(page: Int) =
            BACKSLASH + PAGE_PATH + page
}