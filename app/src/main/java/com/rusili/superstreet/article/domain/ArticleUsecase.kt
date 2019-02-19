package com.rusili.superstreet.article.domain

class ArticleUsecase(private val repository: ArticleRepository) {
    fun getArticleList(href: String) = repository.getArticle(href)
}