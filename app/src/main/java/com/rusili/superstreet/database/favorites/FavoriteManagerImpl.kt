package com.rusili.superstreet.database.favorites

import com.rusili.superstreet.common.models.BaseArticleModel
import com.rusili.superstreet.database.favorites.model.FavoriteEntity
import com.rusili.superstreet.database.favorites.model.FavoriteModelMapper
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoriteManagerImpl @Inject constructor(
    private val dao: FavoriteDao,
    private val mapper: FavoriteModelMapper
) : FavoriteManager {

    override fun saveFavorite(model: BaseArticleModel): Completable =
        dao.addFavorite(
            mapper.from(model)
        )

    override fun removeFavorite(model: BaseArticleModel): Completable =
        dao.removeFavorite(
            mapper.from(model)
        )

    override fun getAllFavorites(): Single<List<BaseArticleModel>> =
        dao.getAllFavorites().map { list ->
            list.map(mapper::to)
        }
}
