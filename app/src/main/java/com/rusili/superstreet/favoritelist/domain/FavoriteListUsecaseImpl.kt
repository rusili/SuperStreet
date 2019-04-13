package com.rusili.superstreet.favoritelist.domain

import com.rusili.superstreet.database.favorites.FavoriteEntity
import com.rusili.superstreet.favoritelist.ui.FavoriteListUsecase
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class FavoriteListUsecaseImpl @Inject constructor(private val repository: FavoriteListRepository) :
    FavoriteListUsecase {

    override fun getAllFavorites(): Flowable<FavoriteEntity> =
        repository.getAllFavorites()

    override fun removeFavorite(entity: FavoriteEntity): Completable =
        repository.removeFavorite(entity)
}
