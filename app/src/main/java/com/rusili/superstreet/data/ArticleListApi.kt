package com.rusili.superstreet.data

import com.rusili.superstreet.domain.model.ArticleFull
import com.rusili.superstreet.domain.model.ArticlePreview
import io.reactivex.Observable
import io.reactivex.Single

interface ArticleListApi {
    fun getArticleList(): Observable<List<ArticlePreview>>
}