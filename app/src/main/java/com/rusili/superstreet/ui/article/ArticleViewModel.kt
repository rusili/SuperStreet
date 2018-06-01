package com.rusili.superstreet.ui.article

import android.arch.lifecycle.MutableLiveData
import com.rusili.superstreet.domain.article.ArticleFullModel
import com.rusili.superstreet.domain.article.ArticleUsecase
import com.rusili.superstreet.ui.common.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticleViewModel(private val usecase: ArticleUsecase) : BaseViewModel() {

    val livedata = MutableLiveData<ArticleFullModel>()

    fun start(href: String) {
        usecase.getArticleList(href)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}