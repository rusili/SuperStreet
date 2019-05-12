package com.rusili.superstreet.common.models.header

import com.rusili.superstreet.common.models.ImageUrl

data class HeaderImage(
    val title: String,
    private val url: ImageUrl
) {

    fun getDefaultSizeUrl() =
        url.resize(660, 440)
}
