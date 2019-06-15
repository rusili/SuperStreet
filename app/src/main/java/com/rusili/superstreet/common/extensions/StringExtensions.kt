package com.rusili.superstreet.common.extensions

import com.rusili.superstreet.jsoup.api.BASE_HTML

fun String.remove(toRemove: String) =
    replace(toRemove, "")
