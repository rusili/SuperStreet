package com.rusili.superstreet.data.list.di

import com.rusili.superstreet.data.di.DataModule
import com.rusili.superstreet.data.list.PreviewListApi
import com.rusili.superstreet.data.list.PreviewListMapper
import com.rusili.superstreet.data.list.PreviewListRepositoryImpl
import com.rusili.superstreet.data.list.PreviewListSerivce
import com.rusili.superstreet.domain.list.ArticleListRepository
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class])
class PreviewListDataModule {

    @Provides
    fun provideArticleListRepository(api: PreviewListApi): ArticleListRepository =
        PreviewListRepositoryImpl(api)

    @Provides
    protected fun provideArticleListApi(
        BASE_HTML: String,
        mapper: PreviewListMapper
    ): PreviewListApi =
        PreviewListSerivce(mapper, BASE_HTML)
}
