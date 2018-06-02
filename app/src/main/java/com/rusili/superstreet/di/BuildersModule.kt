package com.rusili.superstreet.di

import com.rusili.superstreet.ui.MainActivity
import com.rusili.superstreet.ui.article.ArticleActivity
import com.rusili.superstreet.ui.article.di.ArticleModule
import com.rusili.superstreet.ui.list.ArticleListFragment
import com.rusili.superstreet.ui.list.di.ArticleListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ArticleListModule::class])
    abstract fun bindArticleListFragment(): ArticleListFragment

    @ContributesAndroidInjector(modules = [ArticleModule::class])
    abstract fun bindArticleActivity(): ArticleActivity
}