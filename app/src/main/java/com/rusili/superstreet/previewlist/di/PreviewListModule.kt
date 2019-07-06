package com.rusili.superstreet.previewlist.di

import com.rusili.superstreet.database.di.DatabaseModule
import com.rusili.superstreet.jsoup.di.JsoupModule
import com.rusili.superstreet.previewlist.DateHelper
import com.rusili.superstreet.previewlist.data.PreviewListRepositoryImpl
import com.rusili.superstreet.previewlist.domain.ArticleListDataSourceFactory
import com.rusili.superstreet.previewlist.data.PreviewListRepository
import com.rusili.superstreet.previewlist.domain.ArticleListDataSource
import com.rusili.superstreet.previewlist.domain.PreviewListUsecase
import com.rusili.superstreet.previewlist.domain.PreviewListUsecaseImpl
import com.rusili.superstreet.previewlist.ui.PreviewListViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        DatabaseModule::class,
        JsoupModule::class]
)
abstract class PreviewListModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        protected fun provideArticleListViewModelFactory(
            dataSourceFactory: ArticleListDataSourceFactory,
            usecase: PreviewListUsecase
        ) =
            PreviewListViewModelFactory(dataSourceFactory, usecase)

        @JvmStatic
        @Provides
        protected fun provideArticleListDataSourceFactory(dataSource: ArticleListDataSource) =
            ArticleListDataSourceFactory(dataSource).create()

        @JvmStatic
        @Provides
        protected fun provideArticleListDataSource(repository: PreviewListRepository) =
            ArticleListDataSource(repository)

        @JvmStatic
        @Provides
        protected fun provideDateHelper() =
            DateHelper()
    }

    @Binds
    abstract protected fun providePreviewListRepository(repositoryImpl: PreviewListRepositoryImpl): PreviewListRepository

    @Binds
    abstract protected fun providePreviewListUsecase(usecaseImpl: PreviewListUsecaseImpl): PreviewListUsecase
}
