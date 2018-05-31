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
import javax.inject.Inject

private val HREF_KEY = "href_key"

class ArticleFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ArticleViewModelFactory
    private lateinit var viewModel: ArticleViewModel

    companion object {
        fun getInstance(href: String): ArticleFragment {
            val args = Bundle()
            args.putString(HREF_KEY, href)

            return ArticleFragment().apply {
                this.arguments = args
            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            container?.inflate(R.layout.fragment_article)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)

        viewModel.livedata.observe(this, Observer { list ->
            // TODO
        })
    }

    private fun setupViews(view: View) {
        // TODO
    }
}