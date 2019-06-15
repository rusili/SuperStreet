package com.rusili.superstreet.database.favorites

import com.rusili.superstreet.database.favorites.model.FavoriteEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoriteManagerImpl @Inject constructor(private val dao: FavoriteDao) : FavoriteManager {

    override fun toggleFavorite(
        entity: FavoriteEntity,
        enabled: Boolean
    ): Completable =
        when (enabled) {
            true -> dao.removeFavorite(entity)
            false -> dao.addFavorite(entity)
        }

    override fun getAllFavorites(): Single<List<FavoriteEntity>> =
        dao.getAllFavorites()
}
