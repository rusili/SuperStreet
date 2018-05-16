package com.rusili.superstreet.domain

import com.rusili.superstreet.domain.model.ArticlePreview
import io.reactivex.Observable

interface ArticleListRepository{
    fun getArticleList() : Observable<List<ArticlePreview>>
}