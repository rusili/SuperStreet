package com.rusili.superstreet.favoritelist.data

import com.rusili.superstreet.common.models.BaseArticleModel
import com.rusili.superstreet.database.favorites.FavoriteDao
import com.rusili.superstreet.database.favorites.FavoriteManager
import com.rusili.superstreet.database.favorites.model.FavoriteEntity
import com.rusili.superstreet.favoritelist.domain.FavoriteListRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoriteListRepositoryImpl @Inject constructor(private val manager: FavoriteManager) :
    FavoriteListRepository {

    override fun addFavorite(model: BaseArticleModel): Completable =
        manager.saveFavorite(model)

    override fun removeFavorite(model: BaseArticleModel): Completable =
        manager.removeFavorite(model)

    override fun getAllFavorites(): Single<List<BaseArticleModel>> =
        manager.getAllFavorites()
}
