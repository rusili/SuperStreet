package com.rusili.superstreet.ui.list

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.list.ArticleListUsecase
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import com.rusili.superstreet.ui.common.BaseFragment
import com.rusili.superstreet.ui.list.rv.PreviewListAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ArticleListFragment
    : BaseFragment(), ArticleListContract.View {
    @Inject lateinit var usecase: ArticleListUsecase

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
        presenter = ArticleListViewModel(usecase)
        presenter.start(this)
    }

    override fun showPreviewArticles(list: List<ArticlePreviewModel>) {
        testText.text = list.get(0).header.title.value
    }

    override fun goToClickedArticle() {
        navigator.goToArticle()
    }
}