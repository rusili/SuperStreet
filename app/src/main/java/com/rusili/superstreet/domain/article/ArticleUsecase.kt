package com.rusili.superstreet.domain.article

class ArticleUsecase(private val repository: ArticleRepository){
    fun getArticleList() = repository.getArticle()
}