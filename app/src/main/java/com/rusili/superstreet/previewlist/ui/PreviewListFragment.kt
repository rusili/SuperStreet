package com.rusili.superstreet.previewlist.ui

import android.accounts.NetworkErrorException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rusili.superstreet.MainNavigator
import com.rusili.superstreet.R
import com.rusili.superstreet.common.base.BaseFragment
import com.rusili.superstreet.common.extensions.fadeAndHide
import com.rusili.superstreet.common.extensions.isNetworkConnected
import com.rusili.superstreet.common.models.Header
import com.rusili.superstreet.previewlist.DateHelper
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import com.rusili.superstreet.previewlist.ui.rv.PreviewListAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list_loading.*
import timber.log.Timber
import javax.inject.Inject

class PreviewListFragment : BaseFragment() {
    override val TAG: String = PreviewListFragment::class.java.simpleName

    @Inject protected lateinit var dateHelper: DateHelper
    @Inject protected lateinit var viewModelFactory: PreviewListViewModelFactory

    private val viewModel: PreviewListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(PreviewListViewModel::class.java)
    }

    private val navigator: MainNavigator by lazy {
        context as MainNavigator
    }

    private val adapter: PreviewListAdapter by lazy {
        PreviewListAdapter(::onTitleClicked, Glide.with(this), dateHelper).apply {
            setHasStableIds(true)
        }
    }

    companion object {
        fun newInstance() = PreviewListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()

        if (!view.context.isNetworkConnected()) {
            fragmentListErrorView.isVisible = true
            fragmentListLoadingLayout.fadeAndHide()
            showError(NetworkErrorException())
            return
        }

        viewModel.let {
            it.livedata.observe(this@PreviewListFragment, Observer { list ->
                list?.let(::renderData)
            })
            it.loadData()
        }
    }

    private fun setupViews() {
        fragmentListRecyclerView.apply {
            setHasFixedSize(true)
            itemAnimator = null
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false).apply {
                isItemPrefetchEnabled = true
            }
            adapter = this@PreviewListFragment.adapter
        }
    }

    private fun renderData(previewList: PagedList<ArticlePreviewModel>) {
        fragmentListErrorView.isVisible = false

        adapter.submitList(previewList)
        fragmentListLoadingLayout.fadeAndHide()
    }

    private fun onTitleClicked(
        view: View,
        header: Header
    ) {
        if (view.context.isNetworkConnected()) {
            Timber.d("Title: %s Href: %s", header.title.value, header.title.href)
            navigator.goToArticle(view, header)
        } else showError(NetworkErrorException())
    }
}
