package com.rusili.superstreet.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rusili.superstreet.R
import com.rusili.superstreet.ui.common.BaseFragment

class ArticleListFragment : BaseFragment(), ArticleListContract.View {
    private lateinit var presenter: ArticleListViewModel

    companion object {
        fun getInstance() = ArticleListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            inflater?.inflate(R.layout.fragment_list, container, false)

    override fun startPresenter() {
        presenter = ArticleListViewModel()
        presenter.start(this)
    }

    override fun goToClickedArticle(){
        navigator.goToArticle()
    }
}