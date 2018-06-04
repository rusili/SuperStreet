package com.rusili.superstreet.ui.article.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rusili.superstreet.domain.article.ArticleUsecase
import com.rusili.superstreet.ui.article.ArticleViewModel

class ArticleViewModelFactory(private val usecase: ArticleUsecase)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(usecase) as T
        }
        throw IllegalArgumentException("Unknown class $modelClass")
    }
}