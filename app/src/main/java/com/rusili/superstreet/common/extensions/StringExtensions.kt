package com.rusili.superstreet.common.extensions

fun String.remove(toRemove: String) =
    replace(toRemove, "")
