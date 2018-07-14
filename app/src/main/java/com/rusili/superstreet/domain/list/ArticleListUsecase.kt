package com.rusili.superstreet.domain.list

class ArticleListUsecase(private val repository: ArticleListRepository)
    : ArticleListRepository by repository