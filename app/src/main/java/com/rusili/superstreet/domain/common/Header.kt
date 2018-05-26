package com.rusili.superstreet.domain.common

import com.rusili.superstreet.domain.common.header.Image
import com.rusili.superstreet.domain.common.header.Title

data class Header(val title: Title,
                  val image: Image,
                  val desc: String)