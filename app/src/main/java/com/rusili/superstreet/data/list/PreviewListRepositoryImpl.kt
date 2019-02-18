package com.rusili.superstreet.data.list

import com.rusili.superstreet.domain.list.PreviewListRepository
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import javax.inject.Inject

class PreviewListRepositoryImpl @Inject constructor(
    private val api: PreviewListApi,
    private val parser: PreviewListParser
) : PreviewListRepository {

    override fun getArticleStream(page: String?): List<ArticlePreviewModel> =
        parser.parseToList(api.getArticleStream(page))
}
