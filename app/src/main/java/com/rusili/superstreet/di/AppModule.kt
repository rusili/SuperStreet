package com.rusili.superstreet.di

import android.content.Context
import com.rusili.superstreet.App
import dagger.Module
import dagger.Provides

/**
 * Provides app-wide dependencies
 */
@Module
class AppModule {

    @Provides
    fun provideApplicationContext(application: App): Context =
        application.applicationContext
}
