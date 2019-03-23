package com.rusili.superstreet.article.ui

import com.rusili.superstreet.article.domain.ArticleFullModel
import io.reactivex.Single

interface ArticleUsecase {

    fun getArticleOnce(href: String): Single<ArticleFullModel>
}
