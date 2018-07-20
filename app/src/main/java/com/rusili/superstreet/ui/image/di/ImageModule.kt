package com.rusili.superstreet.ui.image.di

import com.rusili.superstreet.ui.util.ImageSaver
import com.rusili.superstreet.ui.util.PermissionsHelper
import dagger.Module
import dagger.Provides

@Module
class ImageModule {

    @Provides
    fun provideImageSaverHelper() =
            ImageSaver()

    @Provides
    fun providePermissionHelper() =
            PermissionsHelper()
}