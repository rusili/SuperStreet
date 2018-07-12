package com.rusili.superstreet.ui.list

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rusili.superstreet.domain.list.ArticleListDataSourceFactory
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import com.rusili.superstreet.ui.common.BaseViewModel

class PreviewViewModel(dataSourceFactory: ArticleListDataSourceFactory) : BaseViewModel() {
    val livedata: LiveData<PagedList<ArticlePreviewModel>>

    init {
        ArticleListDataSourceFactory().create()
        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .build()
        livedata = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
    }
}