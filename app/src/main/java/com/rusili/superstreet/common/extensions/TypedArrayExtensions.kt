package com.rusili.superstreet.common.extensions

import android.content.res.TypedArray

fun TypedArray.getDimen(id: Int) =
    resources.getDimensionPixelSize(id)
