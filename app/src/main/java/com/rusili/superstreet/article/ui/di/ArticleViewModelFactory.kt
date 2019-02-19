package com.rusili.superstreet.article.ui.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rusili.superstreet.article.domain.ArticleUsecase
import com.rusili.superstreet.article.ui.ArticleViewModel

class ArticleViewModelFactory(private val usecase: ArticleUsecase)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(usecase) as T
        }
        throw IllegalArgumentException("Unknown class $modelClass")
    }
}