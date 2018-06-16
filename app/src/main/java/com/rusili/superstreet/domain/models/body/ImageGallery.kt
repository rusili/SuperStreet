package com.rusili.superstreet.domain.models.body

data class ImageGallery(override val id: Int,
                        private val src: String) : AbstractBodyModel(id) {

    override fun getViewType() = ArticleViewType.IMAGE.viewType

    fun resizeToDefaultSize() =
            resize(600, 400)

    fun resizeTo1920By1280(): String =
            resize(1920, 1280, 90)

    // Default Image width & height is 660 x 440
    private fun resize(width: Int,
                       height: Int,
                       quality: Int? = 80): String =
            src.replace("w660", "w" + width.toString())
                    .replace("h440", "h" + height.toString())
                    .replace("q80", "q" + quality.toString())
}