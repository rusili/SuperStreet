package com.rusili.superstreet.previewlist.domain

interface PreviewListRepository{
    fun getArticleStream(page: String? = "") : List<ArticlePreviewModel>
}