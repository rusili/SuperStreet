package com.rusili.superstreet.domain.models.body


data class ImageGroup(override val id: Int,
                      val imageSet: Set<ImageGallery>) : AbstractBodyModel(id) {

    override fun getViewType() = ArticleViewType.IMAGE_GROUP.viewType
}