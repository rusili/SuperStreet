package com.rusili.superstreet.article.ui

import android.accounts.NetworkErrorException
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rusili.superstreet.article.domain.ArticleFullModel
import com.rusili.superstreet.article.ui.rv.ArticleAdapter
import com.rusili.superstreet.common.base.BaseActivity
import com.rusili.superstreet.common.extensions.isNetworkConnected
import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.ImageSize
import com.rusili.superstreet.image.ImageActivity
import com.rusili.superstreet.image.ImageActivity.Companion.IMAGE_BUNDLE_KEY
import com.rusili.superstreet.image.ImageActivity.Companion.IMAGE_SIZE_BUNDLE_KEY
import com.rusili.superstreet.image.ImageActivity.Companion.IMAGE_TRANSITION_NAME_KEY
import kotlinx.android.synthetic.main.activity_article.*
import javax.inject.Inject
import com.rusili.superstreet.R
import com.rusili.superstreet.common.models.Header
import com.rusili.superstreet.common.ui.SimpleRequestListener
import com.squareup.moshi.Moshi

class ArticleActivity : BaseActivity() {
    @Inject protected lateinit var viewModelFactory: ArticleViewModelFactory
    private lateinit var viewModel: ArticleViewModel
    // TODO: Inject Moshi
    private val moshi = Moshi.Builder().build().adapter<Header>(Header::class.java)

    private lateinit var adapter: ArticleAdapter

    companion object {
        const val ARTICLE_HEADER_BUNDLE_KEY = "ARTICLE_HEADER_BUNDLE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition();
        setContentView(R.layout.activity_article)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleViewModel::class.java)
        intent?.getStringExtra(ARTICLE_HEADER_BUNDLE_KEY)?.let { json ->
            val header = moshi.fromJson(json)!!
            setupViews(header)
            articleProgressBar.show()
            viewModel.getArticle(header.title.href)
        } ?: run {
            articleProgressBar.hide()
            showError(IntentSender.SendIntentException())
        }

        viewModel.livedata.observe(this, Observer { wrapper ->
            when {
                wrapper.error != null -> showError(wrapper.error)
                wrapper.data != null -> renderData(wrapper.data)
                else -> showError(UnknownError())
            }
        })
    }

    private fun setupViews(header: Header) {
        // articleContainer.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        articleHeaderImageView.transitionName = header.headerImage.title
        articleHeaderTitle.text = header.title.value

        adapter = ArticleAdapter(::onImageClicked, Glide.with(this))

        articleRecyclerView.apply {
            itemAnimator = null
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false).apply {
                isItemPrefetchEnabled = true
            }
            adapter = this@ArticleActivity.adapter
        }

        Glide.with(this)
            .load(header.headerImage.resizeToDefaultSize())
            .listener(object : SimpleRequestListener() {
                override fun onReadyOrFailed() {
                    supportStartPostponedEnterTransition()
                }
            })
            .into(articleHeaderImageView)
    }

    private fun renderData(article: ArticleFullModel) {
        articleProgressBar.hide()

        adapter.submitList(article.body.getCombinedSections())
    }

    private fun onImageClicked(
        view: View,
        image: Image,
        size: ImageSize
    ) {
        if (view.context.isNetworkConnected()) {
            getWindow().setExitTransition(null)

            Intent(this, ImageActivity::class.java).apply {
                putExtra(IMAGE_BUNDLE_KEY, image)
                putExtra(IMAGE_SIZE_BUNDLE_KEY, size)
                putExtra(IMAGE_TRANSITION_NAME_KEY, image.id.toString())
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@ArticleActivity,
                    view,
                    ViewCompat.getTransitionName(view)!!
                )
                startActivity(this, options.toBundle())
            }
        } else showError(NetworkErrorException())
    }
}
