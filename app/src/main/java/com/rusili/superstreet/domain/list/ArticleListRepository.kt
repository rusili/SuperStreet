package com.rusili.superstreet.domain.list

import com.rusili.superstreet.domain.list.model.ArticlePreview
import io.reactivex.Flowable

interface ArticleListRepository{
    fun getArticleStream() : Flowable<List<ArticlePreview>>
}