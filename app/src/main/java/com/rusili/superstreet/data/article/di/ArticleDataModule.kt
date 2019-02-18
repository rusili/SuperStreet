package com.rusili.superstreet.data.article.di

import com.rusili.superstreet.data.DataModule
import com.rusili.superstreet.data.article.ArticleApi
import com.rusili.superstreet.data.article.ArticleParser
import com.rusili.superstreet.data.article.ArticleRepositoryImpl
import com.rusili.superstreet.data.article.ArticleService
import com.rusili.superstreet.data.util.CommonParser
import com.rusili.superstreet.domain.article.ArticleRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class])
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
