package com.rusili.superstreet.ui.article.di

import com.rusili.superstreet.domain.article.ArticleRepository
import com.rusili.superstreet.domain.article.ArticleUsecase
import dagger.Module
import dagger.Provides

@Module
class ArticleModule(){

    @Provides
    fun provideArticleUsecase(repository: ArticleRepository) =
            ArticleUsecase(repository)

    @Provides
    fun provideArticleViewModelFactory(usecase: ArticleUsecase) =
            ArticleViewModelFactory(usecase)
}