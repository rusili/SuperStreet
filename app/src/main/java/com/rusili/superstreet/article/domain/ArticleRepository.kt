package com.rusili.superstreet.article.domain

import io.reactivex.Single

interface ArticleRepository{
    fun getArticle(href: String) : Single<ArticleFullModel>
}