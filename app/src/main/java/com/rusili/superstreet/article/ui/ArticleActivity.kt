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
import android.animation.LayoutTransition
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.R
import com.rusili.superstreet.common.ui.SimpleRequestListener
import io.reactivex.subjects.CompletableSubject

class ArticleActivity : BaseActivity() {
    @Inject protected lateinit var viewModelFactory: ArticleViewModelFactory
    private lateinit var viewModel: ArticleViewModel

    private lateinit var adapter: ArticleAdapter
    private val onClick: (View, Image, ImageSize) -> Unit = ::onImageClicked

    companion object {
        const val ARTICLE_BUNDLE_KEY = "ARTICLE_BUNDLE_KEY"
        const val ARTICLE_POSITION_BUNDLE_KEY = "ARTICLE_POSITION_BUNDLE_KEY"
        const val ARTICLE_HEADER_IMAGE_BUNDLE_KEY = "ARTICLE_HEADER_IMAGE_BUNDLE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition();
        setContentView(R.layout.activity_article)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleViewModel::class.java)
        intent?.let {
            it.getStringExtra(ARTICLE_HEADER_IMAGE_BUNDLE_KEY)?.let { href ->
                val position = it.getIntExtra(ARTICLE_POSITION_BUNDLE_KEY, -1)
                setupViews(href, position)
            }
            it.getStringExtra(ARTICLE_BUNDLE_KEY)?.let { href ->
                articleProgressBar.show()
                viewModel.getArticle(href)
            }
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

    private fun setupViews(
        href: String,
        position: Int
    ) {
        // articleContainer.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        articleHeaderImageView.transitionName = href + position

        adapter = ArticleAdapter(onClick, Glide.with(this))

        articleRecyclerView.apply {
            itemAnimator = null
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false).apply {
                isItemPrefetchEnabled = true
            }
            adapter = this@ArticleActivity.adapter
        }

        Glide.with(this)
            .load(href)
            .apply(RequestOptions().dontTransform())
            .listener(object : SimpleRequestListener() {
                override fun onReadyOrFailed() {
                    supportStartPostponedEnterTransition()
                }
            })
            .into(articleHeaderImageView)
    }

    private fun renderData(article: ArticleFullModel) {
        articleProgressBar.hide()
        articleHeaderTitle.text = article.header.title.value

        adapter.submitList(article.body.combineSections())
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
