package com.rusili.superstreet.previewlist.ui

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rusili.superstreet.common.base.BaseViewModel
import com.rusili.superstreet.common.models.BaseArticleModel
import com.rusili.superstreet.database.favorites.FavoriteManager
import com.rusili.superstreet.previewlist.domain.ArticleListDataSourceFactory
import com.rusili.superstreet.previewlist.domain.PreviewListUsecase
import io.reactivex.schedulers.Schedulers

class PreviewListViewModel(
    dataSourceFactory: ArticleListDataSourceFactory,
    private val usecase: PreviewListUsecase
) : BaseViewModel() {
    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(1)
        .setPageSize(1)
        .setPrefetchDistance(2)
        .build()

    val livedata = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()

    fun setFavorite(
        model: BaseArticleModel,
        isSelected: Boolean
    ) {
        usecase.toggleFavorite(model, isSelected)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}
