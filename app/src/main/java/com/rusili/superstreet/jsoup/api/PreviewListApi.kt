package com.rusili.superstreet.jsoup.api

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

class PreviewListService @Inject constructor() : PreviewListApi {

    override fun getArticleStream(page: String?): Document =
        Jsoup.connect(Host.BASE_HTML + page)
            .timeout(Host.TIMEOUT_DURATION)
            .get()
}

interface PreviewListApi {
    fun getArticleStream(page: String?): Document
}
