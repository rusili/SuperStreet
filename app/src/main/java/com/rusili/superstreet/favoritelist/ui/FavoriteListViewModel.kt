package com.rusili.superstreet.favoritelist.ui

import androidx.lifecycle.MutableLiveData
import com.rusili.superstreet.common.base.BaseViewModel
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel

class FavoriteListViewModel(private val usecase: FavoriteListUsecase) : BaseViewModel() {
    val livedata = MutableLiveData<List<ArticlePreviewModel>>()

    init {
        loadData()
    }

    fun loadData() {
        usecase.getAllFavorites()
    }
}
