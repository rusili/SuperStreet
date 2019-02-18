package com.rusili.superstreet.domain.list

interface PreviewListRepository{
    fun getArticleStream(page: String? = "") : List<ArticlePreviewModel>
}