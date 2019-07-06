package com.rusili.superstreet.article.data

import com.rusili.superstreet.article.domain.ArticleFullModel
import com.rusili.superstreet.article.domain.ArticleRepository
import com.rusili.superstreet.common.models.BaseArticleModel
import com.rusili.superstreet.database.favorites.FavoriteManager
import com.rusili.superstreet.database.favorites.model.FavoriteModelMapper
import com.rusili.superstreet.jsoup.api.ArticleApi
import com.rusili.superstreet.jsoup.parsing.ArticleParser
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val api: ArticleApi,
    private val parser: ArticleParser,
    private val favoriteManager: FavoriteManager
) : ArticleRepository {

    override fun getArticle(href: String): Single<ArticleFullModel> =
        api.getArticle(href)
            .map(parser::parseToArticle)

    override fun saveFavorite(model: BaseArticleModel): Completable =
        favoriteManager.saveFavorite(model)

    override fun removeFavorite(model: BaseArticleModel): Completable =
        favoriteManager.removeFavorite(model)
}
