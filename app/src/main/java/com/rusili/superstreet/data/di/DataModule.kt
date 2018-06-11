package com.rusili.superstreet.data.di

import com.rusili.superstreet.data.util.BaseMapper
import com.rusili.superstreet.data.util.FlagMapper
import dagger.Module
import dagger.Provides

/**
 * Dagger module:
 * Provides basic dependencies for the data layer.
 */
@Module
class DataModule {

    @Provides
    fun provideBaseHtml() = "http://www.superstreetonline.com"

    @Provides
    fun provideFlagMapper() =
            FlagMapper()
}