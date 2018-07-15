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

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ImageModule::class])
    abstract fun bindImageActivity(): ImageActivity

    @ContributesAndroidInjector(modules = [PreviewListModule::class])
    abstract fun bindArticleListFragment(): PreviewListFragment

    @ContributesAndroidInjector(modules = [ArticleModule::class])
    abstract fun bindArticleActivity(): ArticleActivity
}