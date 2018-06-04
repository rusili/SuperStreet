package com.rusili.superstreet.ui.list

import androidx.lifecycle.MutableLiveData
import com.rusili.superstreet.domain.list.ArticleListUsecase
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import com.rusili.superstreet.ui.common.BaseViewModel
import com.rusili.superstreet.ui.common.LiveDataWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ArticleListViewModel(private val usecase: ArticleListUsecase) : BaseViewModel() {
    val livedata = MutableLiveData<LiveDataWrapper<List<ArticlePreviewModel>>>()

    fun refresh() {
        getArticleList()
    }

    private fun getArticleList() {
        addDisposable(usecase.getArticleStream()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = { livedata.postValue(LiveDataWrapper(it)) },
                        onError = { Timber.e(it, "Error getting preview articles.") }
                ))
    }
}