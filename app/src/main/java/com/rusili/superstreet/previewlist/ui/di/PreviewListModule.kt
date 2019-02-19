package com.rusili.superstreet.previewlist.ui.di

import com.rusili.superstreet.previewlist.domain.ArticleListDataSourceFactory
import com.rusili.superstreet.previewlist.domain.PreviewListRepository
import com.rusili.superstreet.previewlist.DateHelper
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
    protected fun provideArticleListDataSourceFactory(repository: PreviewListRepository) =
            ArticleListDataSourceFactory(repository).create()
}