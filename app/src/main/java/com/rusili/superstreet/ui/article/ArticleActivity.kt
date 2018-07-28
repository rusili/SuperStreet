package com.rusili.superstreet.ui.article

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.article.ArticleFullModel
import com.rusili.superstreet.domain.models.body.AbstractBodyModel
import com.rusili.superstreet.domain.models.body.ArticleHeader
import com.rusili.superstreet.ui.article.di.ArticleViewModelFactory
import com.rusili.superstreet.ui.article.rv.ArticleAdapter
import com.rusili.superstreet.ui.common.BUNDLE_KEY
import com.rusili.superstreet.ui.common.BaseActivity
import com.rusili.superstreet.ui.common.NoIntentException
import com.rusili.superstreet.ui.image.ImageActivity
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import kotlinx.android.synthetic.main.activity_article.*
import javax.inject.Inject

class ArticleActivity : BaseActivity() {
    @Inject lateinit var viewModelFactory: ArticleViewModelFactory
    private lateinit var viewModel: ArticleViewModel

    private lateinit var adapter: ArticleAdapter
    private val onClick: (View, String) -> Unit = this::onTitleClicked
    private lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        setupViews()

        activityArticleToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleViewModel::class.java)
        intent.getStringExtra(BUNDLE_KEY)?.let { href ->
            articleProgressBar.show()
            viewModel.getArticle(href)
        } ?: run {
            articleProgressBar.hide()
            showError(NoIntentException())
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.livedata.observe(this, Observer { wrapper ->
            wrapper?.data?.let { article ->
                renderData(article)
            } ?: showError(wrapper?.error)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        supportFinishAfterTransition()
        super.onBackPressed()
    }

    private fun setupViews() {
        glide = Glide.with(this)
        adapter = ArticleAdapter(onClick, glide)

        articleRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = this@ArticleActivity.adapter
        }
    }

    private fun renderData(article: ArticleFullModel) {
        articleProgressBar.hide()

        val articleHeader = ArticleHeader(0, article.header, article.footer, article.flag)
        val articleBody = article.body.combineLists()

        val combinedList = mutableListOf<AbstractBodyModel>(articleHeader)
        combinedList.addAll(articleBody)
        articleRecyclerView.adapter = AlphaInAnimationAdapter(this@ArticleActivity.adapter)
        adapter.submitList(combinedList)
    }

    private fun onTitleClicked(view: View,
                               href: String) {
        getWindow().setExitTransition(null)

        val intent = Intent(this, ImageActivity::class.java)
        intent.putExtra(BUNDLE_KEY, href)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, getString(R.string.transition_to_image))
        startActivity(intent, options.toBundle())
    }
}