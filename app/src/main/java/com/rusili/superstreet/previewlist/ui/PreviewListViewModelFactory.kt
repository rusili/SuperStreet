package com.rusili.superstreet.previewlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rusili.superstreet.database.favorites.FavoriteManager
import com.rusili.superstreet.previewlist.domain.ArticleListDataSourceFactory
import com.rusili.superstreet.previewlist.domain.PreviewListUsecase

class PreviewListViewModelFactory(
    private val dataSourceFactory: ArticleListDataSourceFactory,
    private val usecase: PreviewListUsecase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PreviewListViewModel::class.java)) {
            return PreviewListViewModel(dataSourceFactory, usecase) as T
        }
        throw IllegalArgumentException("Unknown class $modelClass")
    }
}
