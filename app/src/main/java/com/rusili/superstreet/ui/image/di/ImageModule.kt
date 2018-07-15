package com.rusili.superstreet.ui.image.di

import com.rusili.superstreet.ui.util.ImageNameHelper
import dagger.Module
import dagger.Provides

@Module
class ImageModule {

    @Provides
    fun provideImageNameHelper() =
            ImageNameHelper()
}