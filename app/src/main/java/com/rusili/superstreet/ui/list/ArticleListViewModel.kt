package com.rusili.superstreet.ui.list

import com.rusili.superstreet.ui.common.BaseViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import java.io.IOException

class ArticleListViewModel
    : BaseViewModel(), ArticleListContract.Presenter {
    private var view: ArticleListContract.View? = null

    override fun start(view: ArticleListContract.View) {
        this.view = view
        getPreviewArticles()
    }

    private fun getPreviewArticles() {
        Single.fromCallable {
            val builder = StringBuilder()
            try {
                val webpage = Jsoup.connect("http://www.superstreetonline.com/").get()
                val title = webpage.title()

                builder.append(title).append("\n")

//                for (link in links) {
//                    builder.append("\n").append("Link : ").append(link.attr("href"))
//                            .append("\n").append("Text : ").append(link.text())
//                }
                return@fromCallable builder
            } catch (e: IOException) {
                return@fromCallable builder.append("Error : ").append(e.message)
            }
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { builder ->
                    view?.showPreviewArticles(builder.toString())
                }
    }

    override fun stop() {
        view = null
        onCleared()
    }
}