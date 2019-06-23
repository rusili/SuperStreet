package com.rusili.superstreet.article.domain

import com.rusili.superstreet.common.models.BaseArticleModel
import io.reactivex.Completable
import io.reactivex.Single

interface ArticleRepository{

    fun getArticle(href: String) : Single<ArticleFullModel>

    fun saveFavorite(model: BaseArticleModel): Completable

    fun removeFavorite(model: BaseArticleModel): Completable
}
