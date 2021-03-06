package com.rusili.superstreet.jsoup.api

import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

class ArticleService @Inject constructor() : ArticleApi {

    override fun getArticle(href: String): Single<Document> =
        Single.fromCallable {
            Jsoup.connect(BASE_HTML + href)
                .timeout(TIMEOUT_DURATION)
                .get()
        }
}

interface ArticleApi {
    fun getArticle(href: String): Single<Document>
}
