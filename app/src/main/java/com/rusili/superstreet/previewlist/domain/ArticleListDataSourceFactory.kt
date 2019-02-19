package com.rusili.superstreet.previewlist.domain

import androidx.paging.DataSource
import javax.inject.Inject

class ArticleListDataSourceFactory @Inject constructor(private val repository: PreviewListRepository)
    : DataSource.Factory<Int, ArticlePreviewModel>() {

    override fun create(): DataSource<Int, ArticlePreviewModel> =
        ArticleListDataSource(repository)
}