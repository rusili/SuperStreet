package com.rusili.superstreet.ui.list.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rusili.superstreet.domain.list.ArticleListDataSourceFactory
import com.rusili.superstreet.ui.list.PreviewViewModel

class PreviewListViewModelFactory(private val dataSourceFactory: ArticleListDataSourceFactory)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PreviewViewModel::class.java)) {
            return PreviewViewModel(dataSourceFactory) as T
        }
        throw IllegalArgumentException("Unknown class $modelClass")
    }
}