package com.rusili.superstreet.common.models.header

import com.rusili.superstreet.common.models.ImageUrl

data class HeaderImage(
    val title: String,
    private val url: ImageUrl
) {

    companion object {
        const val HEADER_IMAGE_WIDTH = 540
        const val HEADER_IMAGE_HEIGHT = 360
    }

    fun getDefaultSizeUrl() =
        url.resize(HEADER_IMAGE_WIDTH, HEADER_IMAGE_HEIGHT)
}
