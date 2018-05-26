package com.rusili.superstreet.data.article

import io.reactivex.Single

interface ArticleApi {
    fun getArticle(): Single<ArticleFull>
}