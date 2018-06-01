package com.rusili.superstreet.domain.article

class ArticleUsecase(private val repository: ArticleRepository) {
    fun getArticleList(href: String) = repository.getArticle(href)
}