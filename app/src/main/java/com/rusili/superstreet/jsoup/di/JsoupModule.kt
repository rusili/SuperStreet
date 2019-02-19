package com.rusili.superstreet.jsoup.di

import com.rusili.superstreet.jsoup.api.ArticleApi
import com.rusili.superstreet.jsoup.api.ArticleService
import com.rusili.superstreet.jsoup.api.PreviewListApi
import com.rusili.superstreet.jsoup.api.PreviewListService
import com.rusili.superstreet.jsoup.parsing.ArticleParser
import com.rusili.superstreet.jsoup.parsing.CommonParser
import com.rusili.superstreet.jsoup.parsing.PreviewListParser
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class JsoupModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        protected fun provideArticleParser(commonParser: CommonParser): ArticleParser =
            ArticleParser(commonParser)

        @JvmStatic
        @Provides
        protected fun providePreviewListParser(commonParser: CommonParser): PreviewListParser =
            PreviewListParser(commonParser)

        @JvmStatic
        @Provides
        protected fun provideCommonParser(): CommonParser =
            CommonParser()
    }

    @Binds
    abstract fun providePreviewListApi(service: PreviewListService): PreviewListApi

    @Binds
    abstract fun provideArticleApi(service: ArticleService): ArticleApi
}
