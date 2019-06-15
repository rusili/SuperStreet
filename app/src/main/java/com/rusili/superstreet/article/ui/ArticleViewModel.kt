package com.rusili.superstreet.article.ui

import androidx.lifecycle.MutableLiveData
import com.rusili.superstreet.article.domain.ArticleFullModel
import com.rusili.superstreet.common.base.BaseViewModel
import com.rusili.superstreet.common.ui.LiveDataWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ArticleViewModel(private val usecase: ArticleUsecase) : BaseViewModel() {
    val livedata = MutableLiveData<LiveDataWrapper<ArticleFullModel>>()

    fun getArticle(href: String) {
        addDisposable(usecase.getArticle(href)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { livedata.postValue(LiveDataWrapper(it)) },
                onError = {
                    Timber.e(it, "Error getting preview articles.")
                    livedata.postValue(LiveDataWrapper(null, it))
                }
            ))
    }
}
