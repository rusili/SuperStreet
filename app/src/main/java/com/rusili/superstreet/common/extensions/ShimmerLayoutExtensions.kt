package com.rusili.superstreet.common.extensions

import com.facebook.shimmer.ShimmerFrameLayout

fun ShimmerFrameLayout.fadeAndHide() {
    apply {
        stopShimmer()
        fadeOut()
    }
}
