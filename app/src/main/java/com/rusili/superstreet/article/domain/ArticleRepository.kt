package com.rusili.superstreet.article.domain

import io.reactivex.Single

interface ArticleRepository{

    fun getArticleOnce(href: String) : Single<ArticleFullModel>
}
