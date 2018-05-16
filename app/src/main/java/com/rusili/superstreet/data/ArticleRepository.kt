package com.rusili.superstreet.data

import com.rusili.superstreet.domain.ArticleRepository
import com.rusili.superstreet.domain.model.ArticleFull
import io.reactivex.Single

class ArticleRepositoryImpl(private val articleService: ArticleService)
    : ArticleRepository {
    
    override fun getArticle(): Single<ArticleFull> =
            articleService.getArticleFull()
}