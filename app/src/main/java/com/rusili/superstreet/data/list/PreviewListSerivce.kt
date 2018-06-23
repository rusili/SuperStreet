package com.rusili.superstreet.data.list

import com.rusili.superstreet.domain.list.ArticlePreviewModel
import io.reactivex.Flowable
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject

private const val TIMEOUT_DURATION = 5000

class PreviewListSerivce @Inject constructor(private val previewListMapper: PreviewListMapper,
                                             private val baseHtml: String)
    : PreviewListApi {

    override fun getArticleStream(): Flowable<List<ArticlePreviewModel>> =
            getAllWebsiteData().toFlowable()
                    .map { document ->
                        return@map parseDocumentToList(document)
                    }

    private fun getAllWebsiteData(): Single<Document> =
            Single.fromCallable {
                return@fromCallable Jsoup.connect(baseHtml).timeout(TIMEOUT_DURATION).get()
            }

    private fun parseDocumentToList(document: Document): List<ArticlePreviewModel> =
            previewListMapper.parseToList(document)
}