package com.rusili.superstreet.article.data

import com.rusili.superstreet.article.domain.ArticleFullModel
import com.rusili.superstreet.article.domain.ArticleRepository
import com.rusili.superstreet.jsoup.api.ArticleApi
import com.rusili.superstreet.jsoup.parsing.ArticleParser
import io.reactivex.Single
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val api: ArticleApi,
    private val parser: ArticleParser
) : ArticleRepository {

    override fun getArticle(href: String): Single<ArticleFullModel> =
        api.getArticle(href)
            .map(parser::parseToArticle)
}
