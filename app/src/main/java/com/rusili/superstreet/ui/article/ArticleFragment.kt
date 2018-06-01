package com.rusili.superstreet.ui.article

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rusili.superstreet.R
import com.rusili.superstreet.ui.article.di.ArticleViewModelFactory
import com.rusili.superstreet.ui.common.BaseFragment
import com.rusili.superstreet.ui.inflate
import kotlinx.android.synthetic.main.fragment_article.*
import timber.log.Timber
import javax.inject.Inject

private val BUNDLE_HREF_KEY = "href_key"

class ArticleFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ArticleViewModelFactory
    private lateinit var viewModel: ArticleViewModel

    companion object {
        fun getInstance(href: String): ArticleFragment {
            val args = Bundle()
            args.putString(BUNDLE_HREF_KEY, href)

            return ArticleFragment().apply {
                this.arguments = args
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleViewModel::class.java)
        arguments?.getString(BUNDLE_HREF_KEY)?.let { href ->
            Timber.i("href: ", href)
            viewModel.getArticle(href)
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            container?.inflate(R.layout.fragment_article)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)

        viewModel.livedata.observe(this, Observer { article ->
            articleTitle.text = article?.header?.title?.value
            articleDesc.text = article?.header?.desc
            articleMag.text = article?.flag?.magazine?.value
            articleType.text = article?.flag?.type?.value
            articleAuthorTimestamp.text = article?.footer?.author?.value + article?.footer?.date?.toLocaleString()
        })
    }

    private fun setupViews(view: View) {
        // TODO
    }
}