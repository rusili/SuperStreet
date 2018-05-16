package com.rusili.superstreet.domain.usecase

import com.rusili.superstreet.domain.ArticleRepository

class ArticleUsecase(private val repository: ArticleRepository){
    fun getArticleList() = repository.getArticle()
}