package com.rusili.superstreet.article.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ArticleViewModelFactory @Inject constructor(private val usecase: ArticleUsecase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(usecase) as T
        }
        throw IllegalArgumentException("Unknown class $modelClass")
    }
}
