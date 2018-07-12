package com.rusili.superstreet.domain.list

import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ArticleListDataSourceFactory @Inject constructor(private val disposable: CompositeDisposable,
                                                       private val repository: ArticleListRepository,
                                                       private val searchTerm: String)
    : DataSource.Factory<String, ArticlePreviewModel>() {

    override fun create(): DataSource<String, ArticlePreviewModel> =
            ArticleListDataSource(disposable, repository, searchTerm)
}