package com.rusili.superstreet.article.domain

import com.rusili.superstreet.article.ui.ArticleUsecase
import javax.inject.Inject

class ArticleUsecaseImpl @Inject constructor(private val repository: ArticleRepository) :
    ArticleRepository by repository
