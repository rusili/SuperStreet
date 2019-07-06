package com.rusili.superstreet.previewlist.domain

import com.rusili.superstreet.common.models.BaseArticleModel
import com.rusili.superstreet.database.favorites.model.FavoriteModelMapper
import com.rusili.superstreet.previewlist.data.PreviewListRepository
import io.reactivex.Completable
import javax.inject.Inject

class PreviewListUsecaseImpl @Inject constructor(private val repository: PreviewListRepository) :
    PreviewListUsecase {

    override fun toggleFavorite(
        model: BaseArticleModel,
        isSelected: Boolean
    ): Completable =
        when (isSelected) {
            true -> repository.removeFavorite(model)
            false -> repository.saveFavorite(model)
        }
}
