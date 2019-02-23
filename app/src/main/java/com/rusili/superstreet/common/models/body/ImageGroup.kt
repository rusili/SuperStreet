package com.rusili.superstreet.common.models.body

data class ImageGroup(
    override val id: Int,
    val imageList: List<Image>
) : AbstractBodyModel(id) {

    override fun getViewType() = ArticleViewType.ImageGroup.viewType
}
