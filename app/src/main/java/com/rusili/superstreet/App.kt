package com.rusili.superstreet

import android.app.Activity
import android.app.Application
import com.rusili.superstreet.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

class App : Application(), HasActivityInjector {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}