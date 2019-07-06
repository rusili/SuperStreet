package com.rusili.superstreet.previewlist.ui

import com.rusili.superstreet.common.models.BaseArticleModel

interface PreviewListListener {

    fun setFavorite(
        model: BaseArticleModel,
        isSelected: Boolean
    )
}
