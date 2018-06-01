package com.rusili.superstreet.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.models.header.Title
import com.rusili.superstreet.ui.common.BaseFragment
import com.rusili.superstreet.ui.inflate
import com.rusili.superstreet.ui.list.di.ArticleListViewModelFactory
import com.rusili.superstreet.ui.list.rv.PreviewListAdapter
import javax.inject.Inject

class ArticleListFragment : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ArticleListViewModelFactory
    private lateinit var viewModel: ArticleListViewModel

    private val onClick: (Title) -> Unit = this::onTitleClicked

    private lateinit var recyclerView: RecyclerView
    private val adapter: PreviewListAdapter = PreviewListAdapter(onClick)

    companion object {
        fun getInstance() = ArticleListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            container?.inflate(R.layout.fragment_list)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)

        viewModel.livedata.observe(this, Observer { list ->
            adapter.submitList(list)
        })
    }

    private fun setupViews(view: View) {
        recyclerView = view.findViewById(R.id.fragmentListRecyclerView)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }

    private fun onTitleClicked(title: Title) {
        navigator.goToArticle(title.href)
    }
}