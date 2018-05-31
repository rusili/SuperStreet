package com.rusili.superstreet.ui.article

import android.arch.lifecycle.MutableLiveData
import com.rusili.superstreet.domain.article.ArticleFullModel
import com.rusili.superstreet.domain.article.ArticleUsecase
import com.rusili.superstreet.ui.common.BaseViewModel

class ArticleViewModel(private val usecase: ArticleUsecase) : BaseViewModel() {

    val livedata = MutableLiveData<ArticleFullModel>()

    init {
        start()
    }

    private fun start(){}
}