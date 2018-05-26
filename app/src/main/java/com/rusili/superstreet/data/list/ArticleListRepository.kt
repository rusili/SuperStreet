package com.rusili.superstreet.data.list

import com.rusili.superstreet.domain.list.model.ArticlePreview
import com.rusili.superstreet.domain.list.ArticleListRepository
import io.reactivex.Flowable

class ArticleListRepositoryImpl(private val api: ArticleListApi)
    : ArticleListRepository {

    override fun getArticleStream(): Flowable<List<ArticlePreview>> =
            api.getArticleStream()
}