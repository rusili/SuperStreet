package com.rusili.superstreet.data.list

import com.rusili.superstreet.domain.list.ArticlePreviewModel

interface PreviewListApi {
    fun getArticleStream(page: String?): List<ArticlePreviewModel>
}
