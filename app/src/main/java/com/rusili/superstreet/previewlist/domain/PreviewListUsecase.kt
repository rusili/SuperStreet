package com.rusili.superstreet.previewlist.domain

import io.reactivex.Completable

interface PreviewListUsecase {

    fun saveFavorite(model: ArticlePreviewModel): Completable

    fun removeFavorite(model: ArticlePreviewModel): Completable
}
