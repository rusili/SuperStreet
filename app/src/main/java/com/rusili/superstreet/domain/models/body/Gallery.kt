package com.rusili.superstreet.domain.models.body

data class Gallery(val images: Set<ImageGallery>) : BaseBody {

    override fun getViewType() = ArticleViewType.GALLERY.viewType
}