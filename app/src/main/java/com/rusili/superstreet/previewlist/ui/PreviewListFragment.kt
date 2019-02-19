package com.rusili.superstreet.previewlist.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.rusili.superstreet.R
import com.rusili.superstreet.common.NetworkHelper
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import com.rusili.superstreet.common.models.header.Title
import com.rusili.superstreet.MainNavigator
import com.rusili.superstreet.common.ui.BaseFragment
import com.rusili.superstreet.common.ui.NoNetworkException
import com.rusili.superstreet.previewlist.ui.rv.PreviewListAdapter
import com.rusili.superstreet.previewlist.DateHelper
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class PreviewListFragment : BaseFragment() {
    @Inject protected lateinit var dateHelper: DateHelper
    @Inject protected lateinit var networkHelper: NetworkHelper
    @Inject protected lateinit var viewModelFactory: PreviewListViewModelFactory
    private lateinit var viewModel: PreviewViewModel

    private lateinit var navigator: MainNavigator

    private lateinit var adapter: PreviewListAdapter
    private lateinit var glide: RequestManager
    private val onClick: (View, Title) -> Unit = this::onTitleClicked

    companion object {
        fun newInstance() = PreviewListFragment()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        navigator = context as MainNavigator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PreviewViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        LayoutInflater.from(context).inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        fragmentListProgressBar.show()
        fragmentListSwipeRefresh.isEnabled = false

        viewModel.livedata.observe(this, Observer { list ->
            list?.let { previewList ->
                renderData(previewList)
            }
        })

        viewModel.loadData()
    }

    private fun setupViews() {
        glide = Glide.with(this)
        adapter = PreviewListAdapter(onClick, glide, dateHelper)

        fragmentListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            (layoutManager as LinearLayoutManager).isItemPrefetchEnabled = true
            adapter = AlphaInAnimationAdapter(this@PreviewListFragment.adapter)
        }

        fragmentListSwipeRefresh.apply {
            setProgressViewOffset(false, 150, 250)
            setOnRefreshListener { viewModel.loadData() }
        }
    }

    private fun renderData(previewList: PagedList<ArticlePreviewModel>) {
        fragmentListProgressBar.hide()
        fragmentListSwipeRefresh.isEnabled = true

        adapter.submitList(previewList)
        fragmentListSwipeRefresh.isRefreshing = false
    }

    private fun onTitleClicked(
        view: View,
        title: Title
    ) {
        if (networkHelper.isConnected(view.context)) {
            navigator.goToArticle(view, title.href)
        } else showError(NoNetworkException())
    }
}
