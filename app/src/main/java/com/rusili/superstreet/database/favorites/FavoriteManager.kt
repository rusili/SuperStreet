package com.rusili.superstreet.database.favorites

import com.rusili.superstreet.common.models.BaseArticleModel
import com.rusili.superstreet.database.favorites.model.FavoriteEntity
import com.rusili.superstreet.database.favorites.model.FavoriteModelMapper
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteManager {

    fun saveFavorite(model: BaseArticleModel): Completable

    fun removeFavorite(model: BaseArticleModel): Completable

    fun getAllFavorites(): Single<List<BaseArticleModel>>
}
