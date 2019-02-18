package com.rusili.superstreet.di

import com.rusili.superstreet.App
import com.rusili.superstreet.data.DataModule
import com.rusili.superstreet.data.article.di.ArticleDataModule
import com.rusili.superstreet.data.list.di.PreviewListDataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    BuildersModule::class,
    DataModule::class,
    ArticleDataModule::class,
    PreviewListDataModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App);
}
