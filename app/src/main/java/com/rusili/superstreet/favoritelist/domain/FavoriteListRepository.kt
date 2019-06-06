package com.rusili.superstreet.favoritelist.domain

import com.rusili.superstreet.database.favorites.model.FavoriteEntity
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteListRepository {

    fun getAllFavorites(): Single<List<FavoriteEntity>>

    fun removeFavorite(entity: FavoriteEntity): Completable
}
