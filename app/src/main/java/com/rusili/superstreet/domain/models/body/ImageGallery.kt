package com.rusili.superstreet.domain.models.body

data class ImageGallery(override val id: Int,
                        val hrefSmall: String,
                        val hrefFull: String) : AbstractBodyModel(id) {

    override fun getViewType() = ArticleViewType.IMAGE.viewType
}