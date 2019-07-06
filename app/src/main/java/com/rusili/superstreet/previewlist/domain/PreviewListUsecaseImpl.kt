package com.rusili.superstreet.previewlist.domain

import com.rusili.superstreet.database.favorites.model.FavoriteModelMapper
import com.rusili.superstreet.previewlist.data.PreviewListRepository
import io.reactivex.Completable
import javax.inject.Inject

class PreviewListUsecaseImpl @Inject constructor(
    private val repository: PreviewListRepository,
    private val mapper: FavoriteModelMapper
) : PreviewListUsecase {

    override fun saveFavorite(model: ArticlePreviewModel): Completable =
        repository.saveFavorite(
            mapper.from(model)
        )

    override fun removeFavorite(model: ArticlePreviewModel): Completable =
        repository.removeFavorite(
            mapper.from(model)
        )
}
