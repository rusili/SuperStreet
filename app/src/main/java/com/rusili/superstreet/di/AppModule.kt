package com.rusili.superstreet.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [])
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context =
            application.applicationContext
}