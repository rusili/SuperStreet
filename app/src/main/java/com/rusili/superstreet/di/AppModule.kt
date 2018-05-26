package com.rusili.superstreet.di

import android.content.Context
import com.rusili.superstreet.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: App): Context =
            application.applicationContext
}