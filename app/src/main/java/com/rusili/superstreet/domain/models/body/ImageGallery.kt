package com.rusili.superstreet.domain.models.body

data class ImageGallery(override val id: Int,
                        val hrefSmall: String,
                        val hrefFull: String) : BaseBody2(id) {

    override fun getViewType() = ArticleViewType.GALLERY.viewType
}