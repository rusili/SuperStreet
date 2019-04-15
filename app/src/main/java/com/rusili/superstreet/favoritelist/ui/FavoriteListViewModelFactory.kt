package com.rusili.superstreet.favoritelist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavoriteListViewModelFactory(private val usecase: FavoriteListUsecase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteListViewModel::class.java)) {
            return FavoriteListViewModel(usecase) as T
        }
        throw IllegalArgumentException("Unknown class $modelClass")
    }
}
