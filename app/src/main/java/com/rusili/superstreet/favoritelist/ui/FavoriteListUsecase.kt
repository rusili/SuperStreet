package com.rusili.superstreet.favoritelist.ui

import com.rusili.superstreet.common.models.BaseArticleModel
import com.rusili.superstreet.database.favorites.model.FavoriteEntity
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteListUsecase {

    fun getAllFavorites(): Single<List<BaseArticleModel>>

    fun addFavorite(model: BaseArticleModel): Completable

    fun removeFavorite(model: BaseArticleModel): Completable

    // TODO: sort list
}
