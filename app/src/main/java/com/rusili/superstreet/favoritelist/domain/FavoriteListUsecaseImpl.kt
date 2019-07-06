package com.rusili.superstreet.favoritelist.domain

import com.rusili.superstreet.common.models.BaseArticleModel
import com.rusili.superstreet.database.favorites.model.FavoriteEntity
import com.rusili.superstreet.database.favorites.model.FavoriteModelMapper
import com.rusili.superstreet.favoritelist.ui.FavoriteListUsecase
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.rxkotlin.flatMapSequence
import javax.inject.Inject

class FavoriteListUsecaseImpl @Inject constructor(private val repository: FavoriteListRepository) :
    FavoriteListUsecase {

    override fun addFavorite(model: BaseArticleModel): Completable =
        repository.addFavorite(model)

    override fun removeFavorite(model: BaseArticleModel): Completable =
        repository.removeFavorite(model)

    override fun getAllFavorites(): Single<List<BaseArticleModel>> =
        repository.getAllFavorites()
}
