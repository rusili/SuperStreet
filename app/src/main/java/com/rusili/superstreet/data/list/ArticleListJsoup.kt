package com.rusili.superstreet.data.list

import com.rusili.superstreet.data.SuperStreetParser
import com.rusili.superstreet.domain.list.model.ArticlePreview
import io.reactivex.Flowable
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


class ArticleListJsoup() : ArticleListApi {
    val superStreetParser = SuperStreetParser()

    override fun getArticleStream(): Flowable<List<ArticlePreview>> =
            getAllWebsiteData().toFlowable().map { document ->
                return@map parseDocumentToList(document)
            }

    private fun getAllWebsiteData(): Single<Document> =
            Single.fromCallable {
                return@fromCallable Jsoup.connect("http://www.superstreetonline.com/").get()
            }

    private fun parseDocumentToList(document: Document): List<ArticlePreview> =
            superStreetParser.parseToList(document)

    private fun getWebsite() {
//        Thread(Runnable {
//            val builder = StringBuilder()
//
//            try {
//                val doc = Jsoup.connect("http://www.ssaurel.com/blog").get()
//                val title = doc.title()
//                val links = doc.select("a[href]")
//
//                builder.append(title).append("\n")
//
//                for (link in links) {
//                    builder.append("\n").append("Link : ").append(link.attr("href"))
//                            .append("\n").append("Text : ").append(link.text())
//                }
//            } catch (e: IOException) {
//                builder.append("Error : ").append(e.message)
//            }
//
//            runOnUiThread(Runnable { result.setText(builder.toString()) })
//        }).start()
    }
}