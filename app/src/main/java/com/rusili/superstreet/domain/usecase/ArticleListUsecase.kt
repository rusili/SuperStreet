package com.rusili.superstreet.domain.usecase

import com.rusili.superstreet.domain.ArticleListRepository

class ArticleListUsecase(private val repository: ArticleListRepository){
    fun getArticleList() = repository.getArticleList()
}