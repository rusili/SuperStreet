package com.rusili.superstreet.common.models

import com.rusili.superstreet.common.models.header.HeaderImage
import com.rusili.superstreet.common.models.header.Title

data class Header(
    val title: Title,
    val headerImage: HeaderImage,
    val desc: String
) {

    fun isValid() =
        title.isValid() && headerImage.isValid() && desc.isNotBlank()
}
