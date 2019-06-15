package com.rusili.superstreet.database.favorites

import com.rusili.superstreet.database.favorites.model.FavoriteEntity
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteManager {

    fun toggleFavorite(
        entity: FavoriteEntity,
        enabled: Boolean
    ): Completable

    fun getAllFavorites(): Single<List<FavoriteEntity>>
}
