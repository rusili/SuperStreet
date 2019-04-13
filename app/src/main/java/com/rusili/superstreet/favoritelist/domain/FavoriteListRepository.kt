package com.rusili.superstreet.favoritelist.domain

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rusili.superstreet.database.favorites.FavoriteEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface FavoriteListRepository {

    fun getAllFavorites(): Flowable<FavoriteEntity>

    fun removeFavorite(entity: FavoriteEntity): Completable
}
