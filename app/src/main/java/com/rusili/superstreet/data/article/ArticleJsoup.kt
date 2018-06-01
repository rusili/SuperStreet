package com.rusili.superstreet.data.article

import com.rusili.superstreet.data.SuperStreetMapper
import com.rusili.superstreet.domain.article.ArticleFullModel
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject


class ArticleJsoup @Inject constructor(private val superStreetParser: SuperStreetMapper)
    : ArticleApi {

    override fun getArticle(href: String): Single<ArticleFullModel> =
            getAllWebsiteData(href)
                    .map { document ->
                        return@map parseDocumentToArticle(document)
                    }

    private fun getAllWebsiteData(href: String): Single<Document> =
            Single.fromCallable {
                return@fromCallable Jsoup.connect("http://www.superstreetonline.com/" + href).get()
            }

    private fun parseDocumentToArticle(document: Document): ArticleFullModel =
            superStreetParser.parseToArticle(document)
}