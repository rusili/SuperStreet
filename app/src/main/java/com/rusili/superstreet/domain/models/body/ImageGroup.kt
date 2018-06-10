package com.rusili.superstreet.domain.models.body


data class ImageGroup(override val id: Int,
                      val imageSet: Set<ImageGallery>) : BaseBody2(id) {

    override fun getViewType() = ArticleViewType.IMAGE_GROUP.viewType
}