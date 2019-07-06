package com.rusili.superstreet.previewlist.domain

import com.rusili.superstreet.common.models.BaseArticleModel
import io.reactivex.Completable

interface PreviewListUsecase {

    fun toggleFavorite(
        model: BaseArticleModel,
        isSelected: Boolean
    ): Completable
}
