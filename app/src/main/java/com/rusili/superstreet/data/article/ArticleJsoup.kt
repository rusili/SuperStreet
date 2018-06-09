package com.rusili.superstreet.data.article

import com.rusili.superstreet.domain.article.ArticleFullModel
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

class ArticleJsoup @Inject constructor(private val articleMapper: ArticleMapper,
                                       private val baseHtml: String)
    : ArticleApi {

    override fun getArticle(href: String): Single<ArticleFullModel> =
            getArticleData(href)
                    .map { document ->
                        return@map parseDocumentToArticle(document)
                    }

    private fun getArticleData(href: String): Single<Document> =
            Single.fromCallable {
                return@fromCallable Jsoup.connect(baseHtml + href).get()
            }

    private fun parseDocumentToArticle(document: Document): ArticleFullModel =
            articleMapper.parseToArticle(document)
}