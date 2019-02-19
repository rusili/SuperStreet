package com.rusili.superstreet.previewlist.di

import com.rusili.superstreet.previewlist.data.PreviewListApi
import com.rusili.superstreet.previewlist.data.PreviewListParser
import com.rusili.superstreet.previewlist.data.PreviewListRepositoryImpl
import com.rusili.superstreet.previewlist.data.PreviewListService
import com.rusili.superstreet.common.CommonParser
import com.rusili.superstreet.previewlist.domain.PreviewListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class PreviewListDataModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        protected fun providePreviewListParser(commonParser: CommonParser): PreviewListParser =
            PreviewListParser(commonParser)
    }

    @Binds
    abstract fun providePreviewListApi(service: PreviewListService): PreviewListApi

    @Binds
    abstract fun providePreviewListRepository(repositoryImpl: PreviewListRepositoryImpl): PreviewListRepository
}
