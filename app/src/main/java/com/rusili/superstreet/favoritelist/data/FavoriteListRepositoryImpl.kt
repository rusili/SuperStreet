package com.rusili.superstreet.favoritelist.data

import com.rusili.superstreet.database.favorites.FavoriteDao
import com.rusili.superstreet.database.favorites.FavoriteEntity
import com.rusili.superstreet.favoritelist.domain.FavoriteListRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class FavoriteListRepositoryImpl @Inject constructor(private val dao: FavoriteDao) :
    FavoriteListRepository {

    override fun getAllFavorites(): Flowable<FavoriteEntity> =
        dao.getAllFavorites()

    override fun removeFavorite(entity: FavoriteEntity): Completable =
        dao.removeFavorite(entity)
}
