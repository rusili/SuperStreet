package com.rusili.superstreet.ui.article

import android.arch.lifecycle.MutableLiveData
import com.rusili.superstreet.domain.article.ArticleFullModel
import com.rusili.superstreet.domain.article.ArticleUsecase
import com.rusili.superstreet.ui.common.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ArticleViewModel(private val usecase: ArticleUsecase) : BaseViewModel() {
    val livedata = MutableLiveData<ArticleFullModel>()

    fun getArticle(href: String) {
        addDisposable(usecase.getArticleList(href)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = { livedata.postValue(it) },
                        onError = { Timber.e(it, "Error getting preview articles.") }
                ))
    }
}