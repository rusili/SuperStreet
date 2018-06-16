package com.rusili.superstreet.domain.models

import com.rusili.superstreet.domain.models.header.HeaderImage
import com.rusili.superstreet.domain.models.header.Title

data class Header(val title: Title,
                  val headerImage: HeaderImage,
                  val desc: String)