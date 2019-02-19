package com.rusili.superstreet.previewlist.ui

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rusili.superstreet.previewlist.domain.ArticleListDataSourceFactory
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import com.rusili.superstreet.common.ui.BaseViewModel

class PreviewViewModel(private val dataSourceFactory: ArticleListDataSourceFactory) : BaseViewModel() {
    lateinit var livedata: LiveData<PagedList<ArticlePreviewModel>>

    init {
        loadData()
    }

    fun loadData() {
        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(1)
                .setPageSize(1)
                .setPrefetchDistance(2)
                .build()
        livedata = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
    }
}