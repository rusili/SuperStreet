package com.rusili.superstreet.domain.models.body

data class ImageGallery(override val id: Int,
                        private val url: String) : AbstractBodyModel(id) {

    override fun getViewType() = ArticleViewType.IMAGE.viewType

    fun resizeToDefaultSize() =
            resize(900, 600)

    fun resizeToGroupSize() =
            resize(300, 200)

    fun resizeTo1920By1280(): String =
            resize(1920, 1280, 90)

    // Default Image width & height is 6xx x 4xx
    private fun resize(width: Int,
                       height: Int,
                       quality: Int? = 80): String {
        var resizedUrl = url

        val widthIndex = resizedUrl.indexOf("+w")
        var heightIndex = resizedUrl.indexOf("+h")
        resizedUrl = resizedUrl.replaceRange(widthIndex, heightIndex, "+w$width")

        heightIndex = resizedUrl.indexOf("+h")
        var qualityIndex = resizedUrl.indexOf("+q")
        resizedUrl = resizedUrl.replaceRange(heightIndex, qualityIndex, "+h$height")

        qualityIndex = resizedUrl.indexOf("+q")
        val reIndex = resizedUrl.indexOf("+re")
        return resizedUrl.replaceRange(qualityIndex, reIndex, "+q$quality")
    }
}