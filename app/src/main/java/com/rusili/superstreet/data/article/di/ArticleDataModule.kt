package com.rusili.superstreet.data.article.di

import com.rusili.superstreet.data.article.ArticleApi
import com.rusili.superstreet.data.article.ArticleParser
import com.rusili.superstreet.data.article.ArticleRepositoryImpl
import com.rusili.superstreet.data.article.ArticleService
import com.rusili.superstreet.domain.article.ArticleRepository
import dagger.Module
import dagger.Provides

@Module
class ArticleDataModule {

    companion object {
        val BASE_HTML = "http://www.superstreetonline.com"
    }

    @Provides
    fun provideArticleRepository(api: ArticleApi): ArticleRepository =
        ArticleRepositoryImpl(api)

    @Provides
    protected fun provideArticleApi(mapper: ArticleParser): ArticleApi =
        ArticleService(mapper)

    @Provides
    protected fun provideArticleParser(): ArticleParser =
        ArticleParser()
}
