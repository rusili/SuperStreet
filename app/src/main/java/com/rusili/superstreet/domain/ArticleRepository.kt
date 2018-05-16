package com.rusili.superstreet.domain

import com.rusili.superstreet.domain.model.ArticleFull
import io.reactivex.Single

interface ArticleRepository{
    fun getArticle() : Single<ArticleFull>
}