package com.rusili.superstreet.previewlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rusili.superstreet.previewlist.domain.ArticleListDataSourceFactory

class PreviewListViewModelFactory(private val dataSourceFactory: ArticleListDataSourceFactory) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PreviewListViewModel::class.java)) {
            return PreviewListViewModel(dataSourceFactory) as T
        }
        throw IllegalArgumentException("Unknown class $modelClass")
    }
}
