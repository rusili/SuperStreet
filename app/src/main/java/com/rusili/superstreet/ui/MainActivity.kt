package com.rusili.superstreet.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import com.rusili.superstreet.R
import com.rusili.superstreet.ui.article.ArticleActivity
import com.rusili.superstreet.ui.common.BaseActivity
import com.rusili.superstreet.ui.image.IMAGE_URL_BUNDLE_KEY
import com.rusili.superstreet.ui.list.PreviewListFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseActivity(), MainNavigator, HasSupportFragmentInjector {
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inflateListFragment()
    }

    override fun goToArticle(view: View,
                             href: String) {
        val intent = Intent(this, ArticleActivity::class.java)
        intent.putExtra(IMAGE_URL_BUNDLE_KEY, href)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, getString(R.string.transition_to_image))
        startActivity(intent, options.toBundle())
    }

    private fun inflateListFragment() =
            inflateFragment(PreviewListFragment.getInstance())
}