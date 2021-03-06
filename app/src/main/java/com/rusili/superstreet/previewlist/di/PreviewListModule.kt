package com.rusili.superstreet.previewlist.di

import com.rusili.superstreet.jsoup.di.JsoupModule
import com.rusili.superstreet.previewlist.DateHelper
import com.rusili.superstreet.previewlist.data.PreviewListRepositoryImpl
import com.rusili.superstreet.previewlist.domain.ArticleListDataSourceFactory
import com.rusili.superstreet.previewlist.domain.PreviewListRepository
import com.rusili.superstreet.previewlist.ui.PreviewListViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [JsoupModule::class])
abstract class PreviewListModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        protected fun provideArticleListViewModelFactory(dataSourceFactory: ArticleListDataSourceFactory) =
            PreviewListViewModelFactory(dataSourceFactory)

        @JvmStatic
        @Provides
        protected fun provideArticleListDataSourceFactory(repository: PreviewListRepository) =
            ArticleListDataSourceFactory(repository).create()

        @JvmStatic
        @Provides
        protected fun provideDateHelper() =
            DateHelper()
    }

    @Binds
    abstract protected fun providePreviewListRepository(repositoryImpl: PreviewListRepositoryImpl): PreviewListRepository
}
