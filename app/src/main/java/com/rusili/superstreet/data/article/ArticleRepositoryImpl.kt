package com.rusili.superstreet.data.article

import com.rusili.superstreet.domain.article.ArticleFullModel
import com.rusili.superstreet.domain.article.ArticleRepository
import io.reactivex.Single

class ArticleRepositoryImpl(private val api: ArticleApi)
    : ArticleRepository {

    override fun getArticle(href: String): Single<ArticleFullModel> =
            api.getArticle(href)
}
