package com.rusili.superstreet.article.ui.di

import com.rusili.superstreet.article.domain.ArticleRepository
import com.rusili.superstreet.article.domain.ArticleUsecase
import dagger.Module
import dagger.Provides

@Module
class ArticleModule {

    @Provides
    fun provideArticleViewModelFactory(usecase: ArticleUsecase) =
        ArticleViewModelFactory(usecase)

    @Provides
    protected fun provideArticleUsecase(repository: ArticleRepository) =
        ArticleUsecase(repository)
}
