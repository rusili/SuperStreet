package com.rusili.superstreet.common.models.body

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    override val id: Int,
    private val url: String
) : AbstractBodyModel(id), Parcelable {

    override fun getViewType() = ArticleViewType.Image.viewType

    fun resizeToDefaultSize() =
        resize(720, 480)

    fun resizeToGroupSize() =
        resize(240, 160)

    fun resizeTo1920By1280(): String =
        resize(1920, 1280, 90)

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
