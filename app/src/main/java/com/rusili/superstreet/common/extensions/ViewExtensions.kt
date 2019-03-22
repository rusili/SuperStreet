package com.rusili.superstreet.common.extensions

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.core.view.isVisible

private const val ALPHA_100 = 1f
private const val ALPHA_0 = 0f
private const val DEFAULT_FADE_DURATION: Long = 100

fun View.fadeIn(fadeDuration: Long = DEFAULT_FADE_DURATION) {
    AlphaAnimation(ALPHA_0, ALPHA_100).apply {
        duration = fadeDuration
        fillAfter = true
        startAnimation(this)
    }
    isVisible = true
}

fun View.fadeOut(
    onEndCallback: (() -> Unit)? = null,
    fadeDuration: Long = DEFAULT_FADE_DURATION
) {
    AlphaAnimation(ALPHA_100, ALPHA_0).apply {
        duration = fadeDuration
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) = Unit
            override fun onAnimationRepeat(animation: Animation?) = Unit

            override fun onAnimationEnd(animation: Animation?) {
                onEndCallback?.invoke()
            }
        })
        startAnimation(this)
    }
    isVisible = false
}
