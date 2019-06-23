package com.rusili.superstreet.article.ui

import androidx.lifecycle.MutableLiveData
import com.rusili.superstreet.article.domain.ArticleFullModel
import com.rusili.superstreet.common.base.BaseViewModel
import com.rusili.superstreet.common.ui.LiveDataWrapper
import com.rusili.superstreet.database.favorites.FavoriteManager
import com.rusili.superstreet.database.favorites.model.FavoriteEntity
import com.rusili.superstreet.database.favorites.model.FavoriteModelMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ArticleViewModel(private val usecase: ArticleUsecase) : BaseViewModel() {
    val livedata = MutableLiveData<LiveDataWrapper<ArticleFullModel>>()

    fun getArticle(href: String) {
        addDisposable(
            usecase.getArticle(href)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { livedata.postValue(LiveDataWrapper(it)) },
                    onError = {
                        Timber.e(it, "Error getting article: %s", href)
                        livedata.postValue(LiveDataWrapper(null, it))
                    }
                ))
    }

    fun favorite(
        model: ArticleFullModel,
        toggle: Boolean
    ) {
        addDisposable(
            usecase.toggleFavorite(model, toggle)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = { Timber.e(it, "Error favoriting article: %s", model.header.title.href) }
                )
        )
    }
}
