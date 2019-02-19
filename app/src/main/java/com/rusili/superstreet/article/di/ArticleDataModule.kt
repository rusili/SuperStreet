package com.rusili.superstreet.article.di

import com.rusili.superstreet.jsoup.api.ArticleApi
import com.rusili.superstreet.jsoup.parsing.ArticleParser
import com.rusili.superstreet.article.data.ArticleRepositoryImpl
import com.rusili.superstreet.jsoup.api.ArticleService
import com.rusili.superstreet.jsoup.parsing.CommonParser
import com.rusili.superstreet.article.domain.ArticleRepository
import com.rusili.superstreet.jsoup.di.JsoupModule
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [JsoupModule::class])
abstract class ArticleDataModule {

    @Binds
    abstract fun provideArticleRepository(repositoryImpl: ArticleRepositoryImpl): ArticleRepository
}
