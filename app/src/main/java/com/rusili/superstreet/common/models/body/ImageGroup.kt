package com.rusili.superstreet.common.models.body


data class ImageGroup(
    override val id: Int,
    val imageList: List<ImageGallery>
) : AbstractBodyModel(id) {

    override fun getViewType() = ArticleViewType.IMAGE_GROUP.viewType
}
