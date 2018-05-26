package com.rusili.superstreet.domain.list

import io.reactivex.Flowable

interface ArticleListRepository{
    fun getArticleStream() : Flowable<List<ArticlePreviewModel>>
}