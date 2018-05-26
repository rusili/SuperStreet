package com.rusili.superstreet.data.list

import com.rusili.superstreet.domain.list.model.ArticlePreview
import io.reactivex.Flowable

interface ArticleListApi {
    fun getArticleStream(): Flowable<List<ArticlePreview>>
}