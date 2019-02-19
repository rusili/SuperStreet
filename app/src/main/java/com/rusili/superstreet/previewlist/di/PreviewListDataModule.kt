package com.rusili.superstreet.previewlist.di

import com.rusili.superstreet.jsoup.api.PreviewListApi
import com.rusili.superstreet.jsoup.parsing.PreviewListParser
import com.rusili.superstreet.previewlist.data.PreviewListRepositoryImpl
import com.rusili.superstreet.jsoup.api.PreviewListService
import com.rusili.superstreet.jsoup.di.JsoupModule
import com.rusili.superstreet.jsoup.parsing.CommonParser
import com.rusili.superstreet.previewlist.domain.PreviewListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [JsoupModule::class])
abstract class PreviewListDataModule {

    @Binds
    abstract fun providePreviewListRepository(repositoryImpl: PreviewListRepositoryImpl): PreviewListRepository
}
