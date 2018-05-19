package com.rusili.superstreet.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rusili.superstreet.R
import com.rusili.superstreet.ui.common.BaseFragment

class ArticleFragment : BaseFragment<ArticleFragment>() {

    companion object {
        fun getInstance() = ArticleFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            inflater?.inflate(R.layout.fragment_article, container, false)
}