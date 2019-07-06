package com.rusili.superstreet.home

import android.view.View
import com.rusili.superstreet.common.models.BaseArticleModel
import com.rusili.superstreet.common.models.Header
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel

interface HomeNavigator {

    fun goToArticle(
        view: View,
        model: ArticlePreviewModel
    )
}
