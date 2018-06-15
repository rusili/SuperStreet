package com.rusili.superstreet.data.article

import com.rusili.superstreet.data.di.DataModule
import com.rusili.superstreet.domain.article.ArticleRepository
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(DataModule::class))
class ArticleDataModule {

    @Provides
    fun provideArticleApi(BASE_HTML: String,
                          mapper: ArticleMapper): ArticleApi =
            ArticleService(mapper, BASE_HTML)

    @Provides
    fun provideArticleRepository(api: ArticleApi): ArticleRepository =
            ArticleRepositoryImpl(api)
}