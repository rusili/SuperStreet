package com.rusili.superstreet.ui.article

import androidx.lifecycle.MutableLiveData
import com.rusili.superstreet.domain.article.ArticleFullModel
import com.rusili.superstreet.domain.article.ArticleUsecase
import com.rusili.superstreet.ui.common.BaseViewModel
import com.rusili.superstreet.ui.util.LiveDataWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ArticleViewModel(private val usecase: ArticleUsecase) : BaseViewModel() {
    val livedata = MutableLiveData<LiveDataWrapper<ArticleFullModel>>()

    fun getArticle(href: String) {
        addDisposable(usecase.getArticleList(href)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = { livedata.postValue(LiveDataWrapper(it)) },
                        onError = { Timber.e(it, "Error getting preview articles.") }
                ))
    }
}