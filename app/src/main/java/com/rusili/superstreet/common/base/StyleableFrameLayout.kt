package com.rusili.superstreet.common.base

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.annotation.StyleableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes

abstract class StyleableFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        this.getLayout()?.let {
            inflate(context, it, this@StyleableFrameLayout)
        }

        context.withStyledAttributes(
            attrs,
            this.getStyleable(),
            defStyleAttr, 0
        ) {
            setupViews()
        }
    }

    @LayoutRes
    abstract fun getLayout(): Int?

    abstract fun TypedArray.setupViews()

    @StyleableRes
    fun getStyleable(): IntArray = IntArray(0)
}