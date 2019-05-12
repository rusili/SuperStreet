package com.rusili.superstreet.favoritelist.domain

import com.rusili.superstreet.database.favorites.FavoriteEntity
import com.rusili.superstreet.database.favorites.FavoriteModelMapper
import com.rusili.superstreet.favoritelist.ui.FavoriteListUsecase
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class FavoriteListUsecaseImpl @Inject constructor(
    private val repository: FavoriteListRepository,
    private val mapper: FavoriteModelMapper
) :
    FavoriteListUsecase {

    override fun getAllFavorites(): Single<List<ArticlePreviewModel>> =
        repository.getAllFavorites()
            .toObservable()
            .flatMapIterable { favorites -> favorites }
            .map { favorite -> mapper.to(favorite) }
            .toList()

    override fun removeFavorite(entity: FavoriteEntity): Completable =
        repository.removeFavorite(entity)
}
