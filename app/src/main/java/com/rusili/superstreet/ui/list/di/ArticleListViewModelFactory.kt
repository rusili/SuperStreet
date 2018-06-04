package com.rusili.superstreet.ui.list.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rusili.superstreet.domain.list.ArticleListUsecase
import com.rusili.superstreet.ui.list.ArticleListViewModel

class ArticleListViewModelFactory(private val usecase: ArticleListUsecase)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleListViewModel::class.java)) {
            return ArticleListViewModel(usecase) as T
        }
        throw IllegalArgumentException("Unknown class $modelClass")
    }
}