package com.rusili.superstreet.favoritelist.ui

import com.rusili.superstreet.database.favorites.FavoriteEntity
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteListUsecase {

    fun getAllFavorites(): Single<List<ArticlePreviewModel>>

    fun removeFavorite(entity: FavoriteEntity): Completable

    // TODO: sort list
}
