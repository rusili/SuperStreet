package com.rusili.superstreet.di

import android.content.Context
import com.rusili.superstreet.App
import com.rusili.superstreet.ui.util.NetworkHelper
import dagger.Module
import dagger.Provides

/**
 * Provides app-wide dependencies
 */
@Module
class AppModule {

    @Provides
    fun provideContext(application: App): Context =
        application.applicationContext

    @Provides
    fun provideNetworkHelper() =
        NetworkHelper()
}
