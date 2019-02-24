package com.rusili.superstreet.common.extensions

import android.view.View
import android.view.animation.AlphaAnimation
import androidx.core.view.isVisible

private const val ALPHA_100 = 1f
private const val ALPHA_0 = 0f
private const val DEFAULT_FADE_DURATION: Long = 500

internal fun View.fadeIn(fadeDuration: Long = DEFAULT_FADE_DURATION) {
    AlphaAnimation(ALPHA_0, ALPHA_100).apply {
        duration = fadeDuration
        fillAfter = true
        startAnimation(this)
    }
    isVisible = true
}

internal fun View.fadeOut(fadeDuration: Long = DEFAULT_FADE_DURATION) {
    AlphaAnimation(ALPHA_100, ALPHA_0).apply {
        duration = fadeDuration
        fillAfter = true
        startAnimation(this)
    }
    isVisible = false
}
