package com.rusili.superstreet.domain.models

import com.rusili.superstreet.domain.models.header.Image
import com.rusili.superstreet.domain.models.header.Title

data class Header(val title: Title,
                  val image: Image,
                  val desc: String)