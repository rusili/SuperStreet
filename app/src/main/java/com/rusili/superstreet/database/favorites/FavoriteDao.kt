package com.rusili.superstreet.database.favorites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite_entity")
    fun getAllFavorites(): Single<List<FavoriteEntity>>

    @Insert
    fun addFavorite(entity: FavoriteEntity): Completable

    @Delete
    fun removeFavorite(entity: FavoriteEntity): Completable
}
