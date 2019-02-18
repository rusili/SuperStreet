package com.rusili.superstreet.data.list

import com.rusili.superstreet.data.list.di.PreviewListDataModule
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

private const val TIMEOUT_DURATION = 5000

class PreviewListService @Inject constructor(private val previewListMapper: PreviewListParser) : PreviewListApi {

    override fun getArticleStream(page: String?): List<ArticlePreviewModel> =
        parseDocumentToList(getAllWebsiteData(page))

    private fun parseDocumentToList(document: Document): List<ArticlePreviewModel> =
        previewListMapper.parseToList(document)

    private fun getAllWebsiteData(page: String?): Document =
        Jsoup.connect(PreviewListDataModule.BASE_HTML + page).timeout(TIMEOUT_DURATION).get()
}
