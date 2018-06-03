package com.rusili.superstreet.ui.article

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.bumptech.glide.Glide
import com.rusili.superstreet.R
import com.rusili.superstreet.ui.article.di.ArticleViewModelFactory
import com.rusili.superstreet.ui.common.BUNDLE_KEY
import com.rusili.superstreet.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_article.*
import timber.log.Timber
import javax.inject.Inject

class ArticleActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ArticleViewModelFactory
    private lateinit var viewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleViewModel::class.java)
        intent.getStringExtra(BUNDLE_KEY)?.let { href ->
            Timber.i("href: ", href)
            viewModel.getArticle(href)
        } ?: run {
            // TODO: SHOW ERROR MESSAGE
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.livedata.observe(this, Observer { article ->
            Glide.with(this)
                    .load(article?.header?.image?.src)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(articleThumbnail)

            articleTitle.text = article?.header?.title?.value
            articleDesc.text = article?.header?.desc
            articleMag.text = article?.flag?.magazine?.value
            articleType.text = article?.flag?.type?.value
            articleAuthorTimestamp.text = article?.footer?.author?.value + article?.footer?.date?.toLocaleString()
        })
    }
}