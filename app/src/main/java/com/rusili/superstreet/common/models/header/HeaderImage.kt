package com.rusili.superstreet.common.models.header

data class HeaderImage(
    val title: String,
    private val url: String
) {

    fun resizeToDefaultSize() =
        resize(600, 400)

    // Default Image width & height is 6xx x 4xx
    private fun resize(
        width: Int,
        height: Int,
        quality: Int? = 80
    ): String {
        var resizedUrl = url

        resizedUrl = with(resizedUrl) {
            replaceRange(
                indexOf("+w"),
                indexOf("+h"),
                "+w$width")
        }

        resizedUrl = with(resizedUrl) {
            replaceRange(
                indexOf("+h"),
                indexOf("+q"),
                "+h$height")
        }

        return with(resizedUrl) {
            replaceRange(
                indexOf("+q"),
                indexOf("+re"),
                "+q$quality")
        }
    }
}
