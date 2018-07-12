package com.rusili.superstreet.domain.list

import io.reactivex.Flowable

interface ArticleListRepository{
    fun getArticleStream(page: String) : Flowable<List<ArticlePreviewModel>>
}