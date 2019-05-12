package com.rusili.superstreet.di

import com.rusili.superstreet.MainActivity
import com.rusili.superstreet.article.di.ArticleModule
import com.rusili.superstreet.article.ui.ArticleActivity
import com.rusili.superstreet.database.di.DatabaseModule
import com.rusili.superstreet.favoritelist.di.FavoriteListModule
import com.rusili.superstreet.favoritelist.ui.FavoriteListFragment
import com.rusili.superstreet.image.ImageActivity
import com.rusili.superstreet.previewlist.di.PreviewListModule
import com.rusili.superstreet.previewlist.ui.PreviewListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [AppModule::class])
    abstract fun bindImageActivity(): ImageActivity

    @ContributesAndroidInjector(modules = [AppModule::class, PreviewListModule::class])
    abstract fun bindArticleListFragment(): PreviewListFragment

    @ContributesAndroidInjector(modules = [AppModule::class, DatabaseModule::class, PreviewListModule::class, FavoriteListModule::class])
    abstract fun bindFavoriteListFragment(): FavoriteListFragment

    @ContributesAndroidInjector(modules = [AppModule::class, ArticleModule::class])
    abstract fun bindArticleActivity(): ArticleActivity
}
