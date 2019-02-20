package com.rusili.superstreet.common.models.body

data class Gallery(
    override val id: Int = -1,
    val images: Set<ImageGallery>
) : AbstractBodyModel(id) {

    override fun getViewType() = ArticleViewType.Gallery.viewType
}
