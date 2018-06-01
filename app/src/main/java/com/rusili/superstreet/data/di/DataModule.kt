package com.rusili.superstreet.data.di

import com.rusili.superstreet.data.FlagMapper
import com.rusili.superstreet.data.SuperStreetMapper
import com.rusili.superstreet.data.article.ArticleApi
import com.rusili.superstreet.data.article.ArticleJsoup
import com.rusili.superstreet.data.article.ArticleRepositoryImpl
import com.rusili.superstreet.data.list.ArticleListApi
import com.rusili.superstreet.data.list.ArticleListJsoup
import com.rusili.superstreet.data.list.ArticleListRepositoryImpl
import com.rusili.superstreet.domain.article.ArticleRepository
import com.rusili.superstreet.domain.list.ArticleListRepository
import dagger.Module
import dagger.Provides

/**
 * Dagger module:
 * Provides all dependencies from the data layer.
 */

private const val BASE_HTML = "http://www.superstreetonline.com"

@Module
class DataModule {

    @Provides
    fun provideFlagMapper() =
            FlagMapper()

    @Provides
    fun provideSuperStreetMapper(mapper: FlagMapper) =
            SuperStreetMapper(mapper)

    // Article List Screen
    @Provides
    fun provideArticleListApi(mapper: SuperStreetMapper): ArticleListApi =
            ArticleListJsoup(mapper, BASE_HTML)

    @Provides
    fun provideArticleListRepository(api: ArticleListApi): ArticleListRepository =
            ArticleListRepositoryImpl(api)

    // Full Article Screen
    @Provides
    fun provideArticleApi(mapper: SuperStreetMapper): ArticleApi =
            ArticleJsoup(mapper, BASE_HTML)

    @Provides
    fun provideArticleRepository(api: ArticleApi): ArticleRepository =
            ArticleRepositoryImpl(api)
}