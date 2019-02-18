package com.rusili.superstreet.data.article

import com.rusili.superstreet.domain.article.ArticleFullModel
import com.rusili.superstreet.domain.article.ArticleRepository
import io.reactivex.Single
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val api: ArticleApi,
    private val parser: ArticleParser
) : ArticleRepository {

    override fun getArticle(href: String): Single<ArticleFullModel> =
        api.getArticle(href)
            .map { document ->
                return@map parser.parseToArticle(document)
            }
}
