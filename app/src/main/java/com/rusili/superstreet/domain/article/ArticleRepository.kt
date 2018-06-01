package com.rusili.superstreet.domain.article

import io.reactivex.Single

interface ArticleRepository{
    fun getArticle(href: String) : Single<ArticleFullModel>
}