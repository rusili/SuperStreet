package com.rusili.superstreet.data.article

import com.rusili.superstreet.domain.article.ArticleFullModel
import io.reactivex.Single

interface ArticleApi {
    fun getArticle(href: String): Single<ArticleFullModel>
}