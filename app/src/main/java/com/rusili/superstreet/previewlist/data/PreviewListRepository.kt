package com.rusili.superstreet.previewlist.data

import com.rusili.superstreet.common.models.BaseArticleModel
import com.rusili.superstreet.database.favorites.model.FavoriteEntity
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import io.reactivex.Completable

interface PreviewListRepository {

    fun getArticleStream(page: String? = ""): List<ArticlePreviewModel>

    fun saveFavorite(model: BaseArticleModel): Completable

    fun removeFavorite(model: BaseArticleModel): Completable
}
