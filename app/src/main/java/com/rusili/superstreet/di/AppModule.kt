package com.rusili.superstreet.di

import android.content.Context
import com.rusili.superstreet.SuperApp
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides

/**
 * Provides app-wide dependencies
 */
@Module
class AppModule {

    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder().build()

    @Provides
    fun provideApplicationContext(application: SuperApp): Context =
        application.applicationContext
}
