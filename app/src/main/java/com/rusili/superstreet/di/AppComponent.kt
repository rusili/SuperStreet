package com.rusili.superstreet.di

import com.rusili.superstreet.SuperApp
import com.rusili.superstreet.article.di.ArticleModule
import com.rusili.superstreet.database.di.DatabaseModule
import com.rusili.superstreet.favoritelist.di.FavoriteListModule
import com.rusili.superstreet.jsoup.di.JsoupModule
import com.rusili.superstreet.previewlist.di.PreviewListModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        BuildersModule::class,
        DatabaseModule::class,
        JsoupModule::class,
        ArticleModule::class,
        PreviewListModule::class,
        FavoriteListModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: SuperApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: SuperApp)
}
