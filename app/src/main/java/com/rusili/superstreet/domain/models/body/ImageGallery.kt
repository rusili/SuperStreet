package com.rusili.superstreet.domain.models.body

data class ImageGallery(override val id: Int,
                        private val src: String) : AbstractBodyModel(id) {

    override fun getViewType() = ArticleViewType.IMAGE.viewType

    fun resizeToFull(): String =
            resize(0, 0, 100)

    // Default Image width & height is 660 x 440
    fun resize(width: Int,
               height: Int,
               quality: Int? = 80): String =
            src.replace("w660", "w" + width.toString())
                    .replace("h440", "h" + height.toString())
                    .replace("q80", "q" + quality.toString())
}