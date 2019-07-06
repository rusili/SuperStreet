package com.rusili.superstreet.favoritelist.ui

import androidx.lifecycle.MutableLiveData
import com.rusili.superstreet.common.base.BaseViewModel
import com.rusili.superstreet.common.ui.LiveDataWrapper
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class FavoriteListViewModel(private val usecase: FavoriteListUsecase) : BaseViewModel() {
    val livedata = MutableLiveData<List<ArticlePreviewModel>>()

    init {
        loadData()
    }

    fun loadData() {
        addDisposable(
            usecase.getAllFavorites()
                .map { list ->
                    list.map(ArticlePreviewModel.Companion::fromBaseArticleModel)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = livedata::postValue,
                    onError = { Timber.e(it, "Error getting favorites") }
                ))
    }
}
