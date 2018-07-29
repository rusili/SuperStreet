package com.rusili.superstreet.di

import com.rusili.superstreet.ui.MainActivity
import com.rusili.superstreet.ui.article.ArticleActivity
import com.rusili.superstreet.ui.article.di.ArticleModule
import com.rusili.superstreet.ui.image.ImageActivity
import com.rusili.superstreet.ui.image.di.ImageModule
import com.rusili.superstreet.ui.list.PreviewListFragment
import com.rusili.superstreet.ui.list.di.PreviewListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [AppModule::class, ImageModule::class])
    abstract fun bindImageActivity(): ImageActivity

    @ContributesAndroidInjector(modules = [AppModule::class, PreviewListModule::class])
    abstract fun bindArticleListFragment(): PreviewListFragment

    @ContributesAndroidInjector(modules = [AppModule::class, ArticleModule::class])
    abstract fun bindArticleActivity(): ArticleActivity
}