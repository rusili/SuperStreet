package com.rusili.superstreet.data

import com.rusili.superstreet.domain.model.ArticleFull
import com.rusili.superstreet.domain.model.ArticlePreview
import io.reactivex.Observable
import io.reactivex.Single

interface ArticleApi {
    fun getArticle(): Single<ArticleFull>
}