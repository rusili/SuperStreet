package com.rusili.superstreet.di

import com.rusili.superstreet.MainActivity
import com.rusili.superstreet.article.ui.ArticleActivity
import com.rusili.superstreet.article.ui.di.ArticleModule
import com.rusili.superstreet.image.ImageActivity
import com.rusili.superstreet.previewlist.ui.PreviewListFragment
import com.rusili.superstreet.previewlist.ui.di.PreviewListModule
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

    @ContributesAndroidInjector(modules = [AppModule::class, ArticleModule::class])
    abstract fun bindArticleActivity(): ArticleActivity
}
