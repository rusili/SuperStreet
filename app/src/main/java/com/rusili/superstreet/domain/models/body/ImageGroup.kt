package com.rusili.superstreet.domain.models.body


data class ImageGroup(val id: Int,
                      val imageSet: Set<ImageGallery>) : BaseBody {

    override fun getViewType() = ArticleViewType.IMAGE_GROUP.viewType
}