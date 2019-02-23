package com.rusili.superstreet.article.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rusili.superstreet.R
import com.rusili.superstreet.article.domain.ArticleFullModel
import com.rusili.superstreet.article.ui.rv.ArticleAdapter
import com.rusili.superstreet.common.extensions.isNetworkConnected
import com.rusili.superstreet.common.models.body.AbstractBodyModel
import com.rusili.superstreet.common.models.body.ArticleHeader
import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.ImageSize
import com.rusili.superstreet.common.ui.BaseActivity
import com.rusili.superstreet.common.ui.NoIntentException
import com.rusili.superstreet.common.ui.NoNetworkException
import com.rusili.superstreet.image.ImageActivity
import com.rusili.superstreet.image.ImageActivity.Companion.IMAGE_SIZE_BUNDLE_KEY
import com.rusili.superstreet.image.ImageActivity.Companion.IMAGE_URL_BUNDLE_KEY
import kotlinx.android.synthetic.main.activity_article.*
import javax.inject.Inject

class ArticleActivity : BaseActivity() {
    @Inject protected lateinit var viewModelFactory: ArticleViewModelFactory
    private lateinit var viewModel: ArticleViewModel

    private lateinit var adapter: ArticleAdapter
    private val onClick: (View, Image, ImageSize) -> Unit = ::onImageClicked

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        setupViews()

        activityArticleToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleViewModel::class.java)
        intent.getStringExtra(IMAGE_URL_BUNDLE_KEY)?.let { href ->
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
            wrapper?.data
                ?.let(::renderData)
                ?: showError(wrapper?.error)
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
        adapter = ArticleAdapter(onClick, Glide.with(this))

        articleRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            (layoutManager as LinearLayoutManager).isItemPrefetchEnabled = true
            adapter = this@ArticleActivity.adapter
        }
    }

    private fun renderData(article: ArticleFullModel) {
        articleProgressBar.hide()

        mutableListOf<AbstractBodyModel>().apply {
            add(ArticleHeader(0, article.header, article.footer, article.flag))
            addAll(article.body.combineSections())
        }.also {
            adapter.submitList(it.toList())
        }
    }

    private fun onImageClicked(
        view: View,
        image: Image,
        size: ImageSize
    ) {
        if (view.context.isNetworkConnected()) {
            getWindow().setExitTransition(null)

            Intent(this, ImageActivity::class.java).apply {
                putExtra(IMAGE_URL_BUNDLE_KEY, image)
                putExtra(IMAGE_SIZE_BUNDLE_KEY, size)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@ArticleActivity, view, getString(R.string.transition_to_image))
                startActivity(this, options.toBundle())
            }
        } else showError(NoNetworkException())
    }
}
