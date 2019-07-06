package com.rusili.superstreet.di

import android.content.Context
import com.rusili.superstreet.SuperApp
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import java.util.Date

/**
 * Provides app-wide dependencies
 */
@Module
class AppModule {

    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter().nonNull())
            .build()

    @Provides
    fun provideApplicationContext(application: SuperApp): Context =
        application.applicationContext
}
