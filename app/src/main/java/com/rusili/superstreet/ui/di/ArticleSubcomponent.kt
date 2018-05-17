package com.rusili.superstreet.ui.di

import com.rusili.superstreet.di.AppModule
import com.rusili.superstreet.ui.article.ArticleFragment
import com.rusili.superstreet.ui.list.ArticleListFragment
import dagger.Subcomponent

@Subcomponent(modules = [(AppModule::class), (ArticleModule::class)])
interface ArticleSubcomponent {

    fun inject(fragment: ArticleListFragment)
    fun inject(fragment: ArticleFragment)
}