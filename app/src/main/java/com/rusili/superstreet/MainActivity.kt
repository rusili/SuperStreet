package com.rusili.superstreet

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.rusili.superstreet.article.ui.ArticleActivity
import com.rusili.superstreet.common.base.BaseActivity
import com.rusili.superstreet.image.ImageActivity
import com.rusili.superstreet.image.ImageActivity.Companion.IMAGE_BUNDLE_KEY
import com.rusili.superstreet.previewlist.ui.PreviewListFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseActivity(), MainNavigator, HasSupportFragmentInjector {
    @Inject protected lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inflateListFragment()
    }

    override fun goToArticle(
        view: View,
        href: String
    ) {
        Intent(this, ArticleActivity::class.java).apply {
            putExtra(ArticleActivity.ARTICLE_BUNDLE_KEY, href)
        }.also {
           val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
               this,
               view,
               ViewCompat.getTransitionName(view)!!
           )
            startActivity(it, options.toBundle())
        }
    }

    private fun inflateListFragment() =
        inflateFragment(PreviewListFragment.newInstance())
}
