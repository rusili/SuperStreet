package com.rusili.superstreet.favoritelist.ui

import androidx.room.Delete
import com.rusili.superstreet.database.favorites.FavoriteEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface FavoriteListUsecase {

    fun getAllFavorites(): Flowable<FavoriteEntity>

    fun removeFavorite(entity: FavoriteEntity): Completable

    // TODO: sort list
}
