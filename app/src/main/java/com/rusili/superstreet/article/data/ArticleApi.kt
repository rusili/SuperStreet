package com.rusili.superstreet.article.data

import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

private const val BASE_HTML = "http://www.superstreetonline.com"
private const val TIMEOUT_DURATION = 5000

class ArticleService @Inject constructor() : ArticleApi {

    override fun getArticle(href: String): Single<Document> =
        Single.fromCallable {
            return@fromCallable Jsoup
                .connect(BASE_HTML + href)
                .timeout(TIMEOUT_DURATION)
                .get()
        }
}

interface ArticleApi {
    fun getArticle(href: String): Single<Document>
}
