package com.rusili.superstreet.domain.models.header

data class HeaderImage(val title: String,
                       private val src: String) {

    fun resizeToDefaultSize() =
            resize(600, 400)

    // Default Image width & height is 660 x 440
    private fun resize(width: Int,
                       height: Int,
                       quality: Int? = 80): String =
            src.replace("w660", "w" + width.toString())
                    .replace("h440", "h" + height.toString())
                    .replace("q80", "q" + quality.toString())
}