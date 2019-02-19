package com.rusili.superstreet.previewlist.data

import com.rusili.superstreet.previewlist.domain.PreviewListRepository
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import javax.inject.Inject

class PreviewListRepositoryImpl @Inject constructor(
    private val api: PreviewListApi,
    private val parser: PreviewListParser
) : PreviewListRepository {

    override fun getArticleStream(page: String?): List<ArticlePreviewModel> =
        parser.parseToList(api.getArticleStream(page))
}
