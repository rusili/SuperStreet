package com.rusili.superstreet.data.list

import com.rusili.superstreet.data.SuperStreetMapper
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import io.reactivex.Flowable
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject


class ArticleListJsoup @Inject constructor(private val superStreetParser: SuperStreetMapper)
    : ArticleListApi {

    override fun getArticleStream(): Flowable<List<ArticlePreviewModel>> =
            getAllWebsiteData().toFlowable()
                    .map { document ->
                        return@map parseDocumentToList(document)
                    }

    private fun getAllWebsiteData(): Single<Document> =
            Single.fromCallable {
                return@fromCallable Jsoup.connect("http://www.superstreetonline.com/").get()
            }

    private fun parseDocumentToList(document: Document): List<ArticlePreviewModel> =
            superStreetParser.parseToList(document)
}