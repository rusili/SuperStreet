package com.rusili.superstreet.data.article

import com.rusili.superstreet.data.article.ArticleFull
import io.reactivex.Single

interface ArticleApi {
    fun getArticle(): Single<ArticleFull>
}