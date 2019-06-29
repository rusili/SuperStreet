package com.rusili.superstreet.previewlist.domain

import androidx.paging.DataSource
import com.rusili.superstreet.previewlist.data.PreviewListRepository
import javax.inject.Inject

class ArticleListDataSourceFactory @Inject constructor(private val dataSource: ArticleListDataSource) :
    DataSource.Factory<Int, ArticlePreviewModel>() {

    override fun create(): DataSource<Int, ArticlePreviewModel> = dataSource
}
