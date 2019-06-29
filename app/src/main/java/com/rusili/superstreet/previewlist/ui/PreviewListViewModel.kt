package com.rusili.superstreet.previewlist.ui

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rusili.superstreet.common.base.BaseViewModel
import com.rusili.superstreet.previewlist.domain.ArticleListDataSourceFactory

class PreviewListViewModel(dataSourceFactory: ArticleListDataSourceFactory) : BaseViewModel() {
    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(1)
        .setPageSize(1)
        .setPrefetchDistance(2)
        .build()

    val livedata = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
}
