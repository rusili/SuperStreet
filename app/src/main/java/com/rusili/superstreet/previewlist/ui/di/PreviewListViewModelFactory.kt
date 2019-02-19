package com.rusili.superstreet.previewlist.ui.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rusili.superstreet.previewlist.domain.ArticleListDataSourceFactory
import com.rusili.superstreet.previewlist.ui.PreviewViewModel

class PreviewListViewModelFactory(private val dataSourceFactory: ArticleListDataSourceFactory)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PreviewViewModel::class.java)) {
            return PreviewViewModel(dataSourceFactory) as T
        }
        throw IllegalArgumentException("Unknown class $modelClass")
    }
}