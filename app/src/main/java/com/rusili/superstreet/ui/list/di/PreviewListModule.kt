package com.rusili.superstreet.ui.list.di

import com.rusili.superstreet.domain.list.ArticleListRepository
import com.rusili.superstreet.domain.list.ArticleListUsecase
import dagger.Module
import dagger.Provides

@Module
class PreviewListModule(){

    @Provides
    fun provideArticleListUsecase(repository: ArticleListRepository) =
            ArticleListUsecase(repository)

    @Provides
    fun provideArticleListViewModelFactory(usecase: ArticleListUsecase) =
            PreviewListViewModelFactory(usecase)
}