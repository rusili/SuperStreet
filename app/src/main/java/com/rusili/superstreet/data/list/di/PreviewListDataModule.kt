package com.rusili.superstreet.data.list.di

import com.rusili.superstreet.data.article.ArticleParser
import com.rusili.superstreet.data.list.PreviewListApi
import com.rusili.superstreet.data.list.PreviewListParser
import com.rusili.superstreet.data.list.PreviewListRepositoryImpl
import com.rusili.superstreet.data.list.PreviewListService
import com.rusili.superstreet.domain.list.ArticleListRepository
import dagger.Module
import dagger.Provides

@Module()
class PreviewListDataModule {

    companion object {
        val BASE_HTML = "http://www.superstreetonline.com"
    }

    @Provides
    fun provideArticleListRepository(api: PreviewListApi): ArticleListRepository =
        PreviewListRepositoryImpl(api)

    @Provides
    protected fun provideArticleListApi(mapper: PreviewListParser): PreviewListApi =
        PreviewListService(mapper)

    @Provides
    protected fun providePreviewListParser(): PreviewListParser =
        PreviewListParser()
}
