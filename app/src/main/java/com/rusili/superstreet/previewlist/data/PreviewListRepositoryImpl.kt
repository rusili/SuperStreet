package com.rusili.superstreet.previewlist.data

import com.rusili.superstreet.database.favorites.FavoriteManager
import com.rusili.superstreet.database.favorites.model.FavoriteEntity
import com.rusili.superstreet.jsoup.api.PreviewListApi
import com.rusili.superstreet.jsoup.parsing.PreviewListParser
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import io.reactivex.Completable
import javax.inject.Inject

class PreviewListRepositoryImpl @Inject constructor(
    private val api: PreviewListApi,
    private val favoriteManager: FavoriteManager,
    private val parser: PreviewListParser
) : PreviewListRepository {

    override fun getArticleStream(page: String?): List<ArticlePreviewModel> =
        parser.parseToList(api.getArticleStream(page))

    override fun saveFavorite(entity: FavoriteEntity): Completable =
        favoriteManager.saveFavorite(entity)

    override fun removeFavorite(entity: FavoriteEntity): Completable =
        favoriteManager.removeFavorite(entity)
}
