package com.rusili.superstreet.database.favorites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favoriteentity")
    fun getAllFavorites(): Flowable<FavoriteEntity>

    @Insert
    fun addFavorite(entity: FavoriteEntity): Completable

    @Delete
    fun removeFavorite(entity: FavoriteEntity): Completable
}
