package com.rusili.superstreet.favoritelist.domain

import com.rusili.superstreet.common.models.BaseArticleModel
import com.rusili.superstreet.database.favorites.model.FavoriteEntity
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteListRepository {

    fun getAllFavorites(): Single<List<BaseArticleModel>>

    fun addFavorite(model: BaseArticleModel): Completable

    fun removeFavorite(model: BaseArticleModel): Completable
}
