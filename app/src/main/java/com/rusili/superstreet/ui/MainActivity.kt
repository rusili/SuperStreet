package com.rusili.superstreet.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import com.rusili.superstreet.R
import com.rusili.superstreet.ui.article.ArticleActivity
import com.rusili.superstreet.ui.common.BaseActivity
import com.rusili.superstreet.ui.list.ArticleListFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseActivity(), MainNavigator, HasSupportFragmentInjector {
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        inflateListFragment()
    }

    override fun goToArticle(href: String) =
            goToActivity(ArticleActivity::class.java, href)

    private fun inflateListFragment() =
            inflateFragment(ArticleListFragment.getInstance())
}