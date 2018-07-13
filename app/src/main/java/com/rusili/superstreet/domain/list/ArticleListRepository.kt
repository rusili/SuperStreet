package com.rusili.superstreet.domain.list

interface ArticleListRepository{
    fun getArticleStream(page: String? = "") : List<ArticlePreviewModel>
}