package com.rusili.superstreet.common.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

private const val URL_QUERY_WIDTH = "width="
private const val URL_QUERY_HEIGHT = "height="

private const val URL_QUERY_CHAR = '?'
private const val URL_AND_CHAR = '&'

// TODO: Convert to inline class when Parcelable is supported
@Parcelize
data class ImageUrl(val url: String) : Parcelable {

    // Default Image width & height is 660 x 440
    fun resize(
        width: Int,
        height: Int
    ): String =
        url.substring(0, url.indexOf(URL_QUERY_CHAR) + 1)
            .plus(URL_QUERY_WIDTH + width + URL_AND_CHAR + URL_QUERY_HEIGHT + height)
}
