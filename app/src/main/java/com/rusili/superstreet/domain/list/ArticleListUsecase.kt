package com.rusili.superstreet.domain.list

class ArticleListUsecase(private val repository: ArticleListRepository){
    fun getArticleStream() = repository.getArticleStream()
}