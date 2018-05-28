package com.rusili.superstreet.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import com.rusili.superstreet.R
import com.rusili.superstreet.ui.article.ArticleFragment
import com.rusili.superstreet.ui.common.BaseActivity
import com.rusili.superstreet.ui.list.ArticleListFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


private const val LIST_FRAGMENT_TAG = "List_Fragment_Tag"
private const val ARTICLE_FRAGMENT_TAG = "Article_Fragment_Tag"

class MainActivity
    : BaseActivity(), MainNavigator, HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    private var container = 0

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = R.id.activityFragmentContainer
    }

    override fun onStart() {
        super.onStart()
        inflateListFragment()
    }

    override fun goToArticle() {
    }

    override fun goBackToList() {
        onBackPressed()
    }

    private fun inflateListFragment() {
        supportFragmentManager.beginTransaction()
//                .setCustomAnimations()
                .replace(container, ArticleListFragment.getInstance())
                .addToBackStack(LIST_FRAGMENT_TAG)
                .commit()
    }

    private fun inflateArticleFragment() {
        supportFragmentManager.beginTransaction()
//                .setCustomAnimations()
                .replace(container, ArticleFragment.getInstance())
                .addToBackStack(ARTICLE_FRAGMENT_TAG)
                .commit()
    }
}
