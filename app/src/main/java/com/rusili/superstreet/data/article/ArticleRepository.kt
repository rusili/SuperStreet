package com.rusili.superstreet.data.article

import com.rusili.superstreet.domain.article.ArticleRepository
import io.reactivex.Single

class ArticleRepositoryImpl(private val api: ArticleApi)
    : ArticleRepository {

    override fun getArticle(): Single<ArticleFull> =
            api.getArticle()
}