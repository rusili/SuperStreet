package com.rusili.superstreet.data.list.di

import com.rusili.superstreet.data.DataModule
import com.rusili.superstreet.data.list.PreviewListApi
import com.rusili.superstreet.data.list.PreviewListParser
import com.rusili.superstreet.data.list.PreviewListRepositoryImpl
import com.rusili.superstreet.data.list.PreviewListService
import com.rusili.superstreet.data.util.CommonParser
import com.rusili.superstreet.domain.list.PreviewListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class])
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
