package com.rusili.superstreet.database.favorites

import com.rusili.superstreet.database.favorites.model.FavoriteEntity
import com.rusili.superstreet.database.favorites.model.FavoriteModelMapper
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoriteManagerImpl @Inject constructor(private val dao: FavoriteDao) : FavoriteManager {

    override fun saveFavorite(entity: FavoriteEntity): Completable =
        dao.addFavorite(entity)

    override fun removeFavorite(entity: FavoriteEntity): Completable =
        dao.removeFavorite(entity)

    override fun getAllFavorites(): Single<List<FavoriteEntity>> =
        dao.getAllFavorites()
}
