package com.rusili.superstreet.article.ui

import com.rusili.superstreet.article.domain.ArticleFullModel
import com.rusili.superstreet.common.models.BaseArticleModel
import io.reactivex.Completable
import io.reactivex.Single

interface ArticleUsecase {

    fun getArticle(href: String): Single<ArticleFullModel>

    fun toggleFavorite(
        model: BaseArticleModel,
        toggle: Boolean
    ): Completable
}
