package com.rusili.superstreet.domain.models.body

data class ImageGallery(val id: Int,
                        val hrefSmall: String,
                        val hrefFull: String) : BaseBody {

    override fun getViewType() = ArticleViewType.IMAGE.viewType
}