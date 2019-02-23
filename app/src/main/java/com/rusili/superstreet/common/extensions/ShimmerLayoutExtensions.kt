package com.rusili.superstreet.common.extensions

import androidx.core.view.isVisible
import com.facebook.shimmer.ShimmerFrameLayout

fun ShimmerFrameLayout.stopAndHide() {
    apply {
        stopShimmer()
        isVisible = false
    }
}
