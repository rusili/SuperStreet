package com.rusili.superstreet.data

import com.rusili.superstreet.data.util.CommonParser
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideCommonParser() =
        CommonParser()
}
