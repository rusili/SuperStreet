package com.rusili.superstreet.article.domain

import com.rusili.superstreet.article.ui.ArticleUsecase
import com.rusili.superstreet.common.models.BaseArticleModel
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ArticleUsecaseImpl @Inject constructor(private val repository: ArticleRepository) :
    ArticleUsecase {

    override fun getArticle(href: String): Single<ArticleFullModel> =
        repository.getArticle(href)

    override fun toggleFavorite(
        model: BaseArticleModel,
        toggle: Boolean
    ): Completable =
        when (toggle) {
            true -> repository.saveFavorite(model)
            else -> repository.removeFavorite(model)
        }
}
