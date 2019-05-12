package com.rusili.superstreet.common.models.body

import android.os.Parcelable
import com.rusili.superstreet.common.models.ImageUrl
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    override val id: Int,
    private val url: ImageUrl
) : AbstractBodyModel(id), Parcelable {

    override fun getViewType() = ArticleViewType.Image.viewType

    fun getDefaultSizeUrl() =
        url.resize(720, 480)

    fun getGroupSizeUrl() =
        url.resize(240, 160)

    fun getHighResUrl(): String =
        url.resize(1920, 1280)
}
