package com.rusili.superstreet.ui.article

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.article.ArticleFullModel
import com.rusili.superstreet.ui.article.di.ArticleViewModelFactory
import com.rusili.superstreet.ui.article.rv.ArticleAdapter
import com.rusili.superstreet.ui.common.BUNDLE_KEY
import com.rusili.superstreet.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_article.*
import javax.inject.Inject
import androidx.core.app.ActivityOptionsCompat
import android.content.Intent
import android.view.View
import com.bumptech.glide.RequestManager
import com.rusili.superstreet.ui.image.ImageActivity


class ArticleActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ArticleViewModelFactory
    private lateinit var viewModel: ArticleViewModel

    private lateinit var adapter: ArticleAdapter
    private val onClick: (View, String) -> Unit = this::onTitleClicked
    private lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        setupViews()
        articleProgressBar.show()

        activityArticleToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleViewModel::class.java)
        intent.getStringExtra(BUNDLE_KEY)?.let { href ->
            viewModel.getArticle(href)
        } ?: run {
            showErrorAndFinish(Throwable("Empty intent"))
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.livedata.observe(this, Observer { wrapper ->
            wrapper?.data?.let { article ->
                renderData(article)
            } ?: showErrorAndFinish(wrapper?.error)
        })
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
        val combinedList = article.body.combineLists()
        adapter.submitList(combinedList)
    }

    private fun onTitleClicked(view: View,
                               href: String) {
        getWindow().setExitTransition(null)

        val intent = Intent(this, ImageActivity::class.java)
        intent.putExtra(BUNDLE_KEY, href)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, getString(R.string.transition_image))
        startActivity(intent, options.toBundle())
    }
}