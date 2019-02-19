package com.rusili.superstreet.article.di

import com.rusili.superstreet.article.data.ArticleApi
import com.rusili.superstreet.article.data.ArticleParser
import com.rusili.superstreet.article.data.ArticleRepositoryImpl
import com.rusili.superstreet.article.data.ArticleService
import com.rusili.superstreet.common.CommonParser
import com.rusili.superstreet.article.domain.ArticleRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ArticleDataModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        protected fun provideArticleParser(commonParser: CommonParser): ArticleParser =
            ArticleParser(commonParser)
    }

    @Binds
    abstract fun provideArticleApi(service: ArticleService): ArticleApi

    @Binds
    abstract fun provideArticleRepository(repositoryImpl: ArticleRepositoryImpl): ArticleRepository
}
