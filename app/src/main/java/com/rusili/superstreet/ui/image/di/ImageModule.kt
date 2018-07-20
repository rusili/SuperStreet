package com.rusili.superstreet.ui.image.di

import com.rusili.superstreet.ui.image.util.ImageSaver
import com.rusili.superstreet.ui.image.util.PermissionsHelper
import dagger.Module
import dagger.Provides

/**
 * Provides dependencies for ImageActivity
 */
@Module
class ImageModule {

    @Provides
    fun provideImageSaverHelper() =
            ImageSaver()

    @Provides
    fun providePermissionHelper() =
            PermissionsHelper()
}