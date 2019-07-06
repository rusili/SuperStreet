package com.rusili.superstreet.home

import android.accounts.NetworkErrorException
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.rusili.superstreet.R
import com.rusili.superstreet.article.ui.ArticleActivity
import com.rusili.superstreet.common.base.BaseActivity
import com.rusili.superstreet.common.extensions.isNetworkConnected
import com.rusili.superstreet.common.models.BaseArticleModel
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import com.squareup.moshi.Moshi
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeNavigator, HasSupportFragmentInjector {
    @Inject protected lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject protected lateinit var moshi: Moshi
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityViewPager.adapter =
            MainPagerAdapter(supportFragmentManager)
    }

    override fun goToArticle(
        view: View,
        model: ArticlePreviewModel
    ) {
        if (!isNetworkConnected()) {
            showError(NetworkErrorException())
        }

        Intent(this, ArticleActivity::class.java).apply {
            putExtra(
                ArticleActivity.ARTICLE_BUNDLE_KEY,
                moshi.adapter(ArticlePreviewModel::class.java).toJson(model)
            )
        }.also {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                view,
                ViewCompat.getTransitionName(view).orEmpty()
            )
            startActivity(it, options.toBundle())
        }
    }
}
