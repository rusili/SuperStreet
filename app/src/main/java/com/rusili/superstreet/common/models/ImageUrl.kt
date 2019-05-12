package com.rusili.superstreet.common.models

private const val URL_QUERY_WIDTH = "width="
private const val URL_QUERY_HEIGHT = "height="

private const val URL_QUERY_CHAR = '?'
private const val URL_AND_CHAR = '&'

inline class ImageUrl(val url: String) {

    // Default Image width & height is 660 x 440
    fun resize(
        width: Int,
        height: Int
    ): String =
        url.substring(0, url.indexOf(URL_QUERY_CHAR) + 1)
            .plus(URL_QUERY_WIDTH + width + URL_AND_CHAR + URL_QUERY_HEIGHT + height)
}
