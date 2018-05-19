package com.rusili.superstreet.ui.list

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rusili.superstreet.R
import com.rusili.superstreet.ui.common.BaseFragment
import com.rusili.superstreet.ui.list.rv.PreviewListAdapter

class ArticleListFragment
    : BaseFragment(), ArticleListContract.View {
    private lateinit var presenter: ArticleListViewModel

    private lateinit var recyclerView: RecyclerView

    companion object {
        fun getInstance() = ArticleListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            inflater?.inflate(R.layout.fragment_list, container, false)

    override fun setupViews(view: View) {
        recyclerView = view.findViewById(R.id.fragmentListRecyclerView)
        recyclerView.adapter = PreviewListAdapter()
    }

    override fun startPresenter() {
        presenter = ArticleListViewModel()
        presenter.start(this)
    }

    override fun goToClickedArticle(){
        navigator.goToArticle()
    }
}