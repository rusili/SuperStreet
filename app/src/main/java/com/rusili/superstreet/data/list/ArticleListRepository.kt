package com.rusili.superstreet.data.list

import com.rusili.superstreet.domain.list.ArticlePreviewModel
import com.rusili.superstreet.domain.list.ArticleListRepository
import io.reactivex.Flowable
import javax.inject.Inject

class ArticleListRepositoryImpl @Inject constructor(private val api: ArticleListApi)
    : ArticleListRepository {

    override fun getArticleStream(): Flowable<List<ArticlePreviewModel>> =
            api.getArticleStream()
}