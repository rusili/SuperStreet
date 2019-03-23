package com.rusili.superstreet.article.data

import com.rusili.superstreet.article.domain.ArticleFullModel
import com.rusili.superstreet.article.domain.ArticleRepository
import com.rusili.superstreet.jsoup.api.ArticleApi
import com.rusili.superstreet.jsoup.parsing.ArticleParser
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val api: ArticleApi,
    private val parser: ArticleParser
) : ArticleRepository {

    override fun getArticleOnce(href: String): Single<ArticleFullModel> =
        api.getArticle(href)
            .map { document ->
                Timber.d("Href: %s", href)
                return@map parser.parseToArticle(document)
            }
}
