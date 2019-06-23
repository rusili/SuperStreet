package com.rusili.superstreet.database.favorites

import com.rusili.superstreet.database.favorites.model.FavoriteEntity
import com.rusili.superstreet.database.favorites.model.FavoriteModelMapper
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteManager {

    fun saveFavorite(entity: FavoriteEntity): Completable

    fun removeFavorite(entity: FavoriteEntity): Completable

    fun getAllFavorites(): Single<List<FavoriteEntity>>
}
