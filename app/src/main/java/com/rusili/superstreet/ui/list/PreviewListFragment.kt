package com.rusili.superstreet.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import com.rusili.superstreet.domain.models.header.Title
import com.rusili.superstreet.ui.common.BaseFragment
import com.rusili.superstreet.ui.list.di.PreviewListViewModelFactory
import com.rusili.superstreet.ui.list.rv.PreviewListAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class PreviewListFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: PreviewListViewModelFactory
    private lateinit var viewModel: PreviewViewModel

    private lateinit var adapter: PreviewListAdapter
    private val onClick: (Title) -> Unit = this::onTitleClicked
    private lateinit var glide: RequestManager

    companion object {
        fun getInstance() = PreviewListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PreviewViewModel::class.java)
        viewModel.refresh()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            LayoutInflater.from(context).inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View,
                               savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        fragmentListProgressBar.show()

        viewModel.livedata.observe(this, Observer { wrapper ->
            wrapper?.data?.let { previewList ->
                renderData(previewList)
            } ?: navigator.showError(wrapper?.error)
        })
    }

    private fun setupViews() {
        glide = Glide.with(this)
        adapter = PreviewListAdapter(onClick, glide)

        fragmentListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = this@PreviewListFragment.adapter
        }

        fragmentListSwipeRefresh.apply {
            setProgressViewOffset(false, 150, 250)
            setOnRefreshListener { viewModel.refresh() }
        }
    }

    private fun renderData(previewList: List<ArticlePreviewModel>) {
        fragmentListProgressBar.hide()
        adapter.submitList(previewList)
        fragmentListSwipeRefresh.isRefreshing = false
    }

    private fun onTitleClicked(title: Title) {
        navigator.goToArticle(title.href)
    }
}