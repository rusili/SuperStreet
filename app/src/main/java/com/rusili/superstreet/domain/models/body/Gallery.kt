package com.rusili.superstreet.domain.models.body

data class Gallery(override val id: Int = -1,
                   val images: Set<ImageGallery>) : BaseBody2(id) {

    override fun getViewType() = ArticleViewType.GALLERY.viewType
}