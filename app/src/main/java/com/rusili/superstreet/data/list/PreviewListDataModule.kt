package com.rusili.superstreet.data.list

import com.rusili.superstreet.data.di.DataModule
import com.rusili.superstreet.domain.list.ArticleListRepository
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(DataModule::class))
class PreviewListDataModule {

    @Provides
    fun provideArticleListApi(BASE_HTML: String,
                              mapper: PreviewListMapper): PreviewListApi =
            PreviewListJsoup(mapper, BASE_HTML)

    @Provides
    fun provideArticleListRepository(api: PreviewListApi): ArticleListRepository =
            PreviewListRepositoryImpl(api)
}