package com.rusili.superstreet.domain.article

import com.rusili.superstreet.data.article.ArticleFull
import io.reactivex.Single

interface ArticleRepository{
    fun getArticle() : Single<ArticleFull>
}