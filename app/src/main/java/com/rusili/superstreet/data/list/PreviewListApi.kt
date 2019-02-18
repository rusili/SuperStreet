package com.rusili.superstreet.data.list

import com.rusili.superstreet.data.DataModule
import com.rusili.superstreet.data.list.di.PreviewListDataModule
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

private const val BASE_HTML = "http://www.superstreetonline.com"
private const val TIMEOUT_DURATION = 5000

class PreviewListService @Inject constructor() : PreviewListApi {

    override fun getArticleStream(page: String?): Document =
        Jsoup.connect(BASE_HTML + page).timeout(TIMEOUT_DURATION).get()
}

interface PreviewListApi {
    fun getArticleStream(page: String?): Document
}
