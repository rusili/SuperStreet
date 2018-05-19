package com.rusili.superstreet.ui

import android.os.Bundle
import com.rusili.superstreet.R
import com.rusili.superstreet.ui.article.ArticleFragment
import com.rusili.superstreet.ui.common.BaseActivity
import com.rusili.superstreet.ui.list.ArticleListFragment

private const val LIST_FRAGMENT_TAG = "List_Fragment_Tag"
private const val ARTICLE_FRAGMENT_TAG = "Article_Fragment_Tag"

class MainActivity : BaseActivity(), MainNavigator {
    private var container = 0

    override fun onCreate(savedInstanceState: Bundle?) {
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
    }

    private fun inflateListFragment() {
        fragmentManager.beginTransaction()
//                .setCustomAnimations()
                .replace(container, ArticleListFragment.getInstance())
                .addToBackStack(LIST_FRAGMENT_TAG)
                .commit()
    }

    private fun inflateArticleFragment() {
        fragmentManager.beginTransaction()
//                .setCustomAnimations()
                .replace(container, ArticleFragment.getInstance())
                .addToBackStack(ARTICLE_FRAGMENT_TAG)
                .commit()
    }
}
