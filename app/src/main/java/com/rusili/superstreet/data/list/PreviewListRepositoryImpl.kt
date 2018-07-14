package com.rusili.superstreet.data.list

import com.rusili.superstreet.domain.list.ArticleListRepository
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import io.reactivex.Flowable
import javax.inject.Inject

class PreviewListRepositoryImpl @Inject constructor(private val api: PreviewListApi)
    : ArticleListRepository {

    override fun getArticleStream(page: String?): List<ArticlePreviewModel> =
            api.getArticleStream(page)
}