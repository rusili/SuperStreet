package com.rusili.superstreet.domain.models.header

data class HeaderImage(val title: String,
                       private val src: String) {

    // Default Image width & height is 660 x 440
    fun resize(width: Int,
               height: Int,
               quality: Int? = 80): String =
            src.replace("w660", "w" + width.toString())
                    .replace("h440", "h" + height.toString())
                    .replace("q80", "q" + quality.toString())
}