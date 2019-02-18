package com.rusili.superstreet.domain.list

import androidx.paging.DataSource
import javax.inject.Inject

class ArticleListDataSourceFactory @Inject constructor(private val repository: ArticleListRepository)
    : DataSource.Factory<Int, ArticlePreviewModel>() {

    override fun create(): DataSource<Int, ArticlePreviewModel> =
            ArticleListDataSource(repository)
}