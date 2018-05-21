package com.rusili.superstreet.data

import com.rusili.superstreet.domain.ArticleListRepository
import com.rusili.superstreet.domain.model.ArticlePreview
import io.reactivex.Observable

class ArticleListRepositoryImpl(private val api: ArticleListApi)
    : ArticleListRepository {

    override fun getArticleList(): Observable<List<ArticlePreview>> =
            api.getArticleList()
}