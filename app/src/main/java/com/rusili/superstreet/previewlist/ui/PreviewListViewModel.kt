package com.rusili.superstreet.previewlist.ui

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rusili.superstreet.common.base.BaseViewModel
import com.rusili.superstreet.previewlist.domain.ArticleListDataSourceFactory
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel

class PreviewListViewModel(private val dataSourceFactory: ArticleListDataSourceFactory) : BaseViewModel() {
    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(1)
        .setPageSize(1)
        .setPrefetchDistance(2)
        .build()

    lateinit var livedata: LiveData<PagedList<ArticlePreviewModel>>

    init {
        loadData()
    }

    fun loadData() {
        livedata = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
    }
}
