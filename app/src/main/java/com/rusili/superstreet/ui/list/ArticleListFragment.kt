package com.rusili.superstreet.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import com.rusili.superstreet.domain.models.header.Title
import com.rusili.superstreet.ui.common.BaseFragment
import com.rusili.superstreet.ui.list.di.ArticleListViewModelFactory
import com.rusili.superstreet.ui.list.rv.PreviewListAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ArticleListFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ArticleListViewModelFactory
    private lateinit var viewModel: ArticleListViewModel

    private lateinit var progressBar: ContentLoadingProgressBar

    private val onClick: (Title) -> Unit = this::onTitleClicked

    private val adapter: PreviewListAdapter = PreviewListAdapter(onClick)

    companion object {
        fun getInstance() = ArticleListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleListViewModel::class.java)
        viewModel.refresh()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            LayoutInflater.from(context).inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        progressBar.show()

        viewModel.livedata.observe(this, Observer { wrapper ->
            wrapper?.data?.let { previewList ->
                renderData(previewList)
            } ?: navigator.showError(wrapper?.error)
        })
    }

    private fun renderData(previewList: List<ArticlePreviewModel>) {
        progressBar.hide()
        adapter.submitList(previewList)
        fragmentListSwipeRefresh.isRefreshing = false
    }

    private fun setupViews(view: View) {
        progressBar = view.findViewById(R.id.fragmentListProgressBar)

        fragmentListRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        fragmentListRecyclerView.itemAnimator = DefaultItemAnimator()
        fragmentListRecyclerView.adapter = adapter

        fragmentListSwipeRefresh.setProgressViewOffset(false, 150, 250)
        fragmentListSwipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun onTitleClicked(title: Title) {
        navigator.goToArticle(title.href)
    }
}