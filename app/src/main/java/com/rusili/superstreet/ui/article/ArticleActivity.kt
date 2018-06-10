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
import com.rusili.superstreet.ui.list.rv.PreviewListAdapter
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ArticleActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ArticleViewModelFactory
    private lateinit var viewModel: ArticleViewModel

    private val adapter: ArticleAdapter = ArticleAdapter()

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
        articleRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = this@ArticleActivity.adapter
        }
    }

    private fun renderData(article: ArticleFullModel) {
        articleProgressBar.hide()
        adapter.submitList(article.body.paragraphs)
    }
}