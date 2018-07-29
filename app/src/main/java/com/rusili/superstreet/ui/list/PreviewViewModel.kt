package com.rusili.superstreet.ui.list

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rusili.superstreet.domain.list.ArticleListDataSourceFactory
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import com.rusili.superstreet.ui.common.BaseViewModel

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