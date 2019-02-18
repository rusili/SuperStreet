package com.rusili.superstreet.ui.list.di

import com.rusili.superstreet.domain.list.ArticleListDataSourceFactory
import com.rusili.superstreet.domain.list.ArticleListRepository
import com.rusili.superstreet.ui.util.DateHelper
import dagger.Module
import dagger.Provides

@Module
class PreviewListModule() {

    @Provides
    fun provideDateHelper() =
            DateHelper()

    @Provides
    fun provideArticleListViewModelFactory(dataSourceFactory: ArticleListDataSourceFactory) =
            PreviewListViewModelFactory(dataSourceFactory)

    @Provides
    protected fun provideArticleListDataSourceFactory(repository: ArticleListRepository) =
            ArticleListDataSourceFactory(repository).create()
}