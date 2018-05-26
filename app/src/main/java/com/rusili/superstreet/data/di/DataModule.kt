package com.rusili.superstreet.data.di

import com.rusili.superstreet.data.SuperStreetMapper
import com.rusili.superstreet.data.list.ArticleListApi
import com.rusili.superstreet.data.list.ArticleListJsoup
import com.rusili.superstreet.data.list.ArticleListRepositoryImpl
import com.rusili.superstreet.domain.list.ArticleListRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideSuperStreetMapper() =
            SuperStreetMapper()

    @Provides
    fun provideArticleListApi(mapper: SuperStreetMapper): ArticleListApi =
            ArticleListJsoup(mapper)

    @Provides
    fun provideArticleListRepository(api: ArticleListApi): ArticleListRepository =
            ArticleListRepositoryImpl(api)
}