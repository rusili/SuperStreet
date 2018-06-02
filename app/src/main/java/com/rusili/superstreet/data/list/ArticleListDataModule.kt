package com.rusili.superstreet.data.list

import com.rusili.superstreet.data.SuperStreetMapper
import com.rusili.superstreet.data.di.DataModule
import com.rusili.superstreet.domain.list.ArticleListRepository
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(DataModule::class))
class ArticleListDataModule {

    @Provides
    fun provideArticleListApi(BASE_HTML: String,
                              mapper: SuperStreetMapper): ArticleListApi =
            ArticleListJsoup(mapper, BASE_HTML)

    @Provides
    fun provideArticleListRepository(api: ArticleListApi): ArticleListRepository =
            ArticleListRepositoryImpl(api)
}