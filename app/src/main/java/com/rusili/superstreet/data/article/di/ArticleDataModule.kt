package com.rusili.superstreet.data.article.di

import com.rusili.superstreet.data.article.ArticleApi
import com.rusili.superstreet.data.article.ArticleMapper
import com.rusili.superstreet.data.article.ArticleRepositoryImpl
import com.rusili.superstreet.data.article.ArticleService
import com.rusili.superstreet.data.di.DataModule
import com.rusili.superstreet.domain.article.ArticleRepository
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class])
class ArticleDataModule {

    @Provides
    fun provideArticleRepository(api: ArticleApi): ArticleRepository =
        ArticleRepositoryImpl(api)

    @Provides
    protected fun provideArticleApi(
        BASE_HTML: String,
        mapper: ArticleMapper
    ): ArticleApi =
        ArticleService(mapper, BASE_HTML)
}
