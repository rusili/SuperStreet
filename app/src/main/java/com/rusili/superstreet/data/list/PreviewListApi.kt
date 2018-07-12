package com.rusili.superstreet.data.list

import com.rusili.superstreet.domain.list.ArticlePreviewModel
import io.reactivex.Flowable

interface PreviewListApi {
    fun getArticleStream(page: String): Flowable<List<ArticlePreviewModel>>
}