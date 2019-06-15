package com.rusili.superstreet.common.ui

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.annotation.StyleableRes
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
            defStyleAttr,
            0
        ) {
            setupViews()
        }

        post(::postSetup)
    }

    @LayoutRes
    abstract fun getLayout(): Int?

    abstract fun TypedArray.setupViews()

    @StyleableRes
    open fun getStyleable(): IntArray = IntArray(0)

    open fun postSetup() = Unit
}
