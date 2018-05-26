package com.rusili.superstreet.ui.di

import com.rusili.superstreet.domain.list.ArticleListRepository
import com.rusili.superstreet.domain.list.ArticleListUsecase
import dagger.Module
import dagger.Provides

@Module
class ArticleModule(){

    @Provides
    fun provideArticleListUsecase(repository: ArticleListRepository) =
            ArticleListUsecase(repository)
}