package com.rusili.superstreet.common.ui

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.Shape
import android.os.Build
import android.util.AttributeSet
import android.widget.ImageButton
import androidx.annotation.ColorInt
import androidx.core.content.withStyledAttributes
import com.rusili.superstreet.R

class ShapedRippleImageButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageButton(context, attrs, defStyleAttr) {

    init {
        context.withStyledAttributes(
            attrs,
            IntArray(0),
            defStyleAttr,
            0
        ) {
            setupViews()
        }
    }

    private fun setupViews() {
        val buttonShape = OvalShape()

        @ColorInt val fillColor = resources.getColor(android.R.color.transparent)
        @ColorInt val maskColor = resources.getColor(R.color.black_30_1000)

        background?.let {
            setBackgroundRippleShape(it, buttonShape)
        } ?: setForegroundRippleShape(buttonShape, fillColor, maskColor)
    }

    private fun setBackgroundRippleShape(
        background: Drawable,
        buttonShape: Shape
    ) {
        (background as RippleDrawable).apply {
            (getDrawable(0) as ShapeDrawable).apply { shape = buttonShape }
            (getDrawable(1) as ShapeDrawable).apply { shape = buttonShape }
        }
    }

    private fun setForegroundRippleShape(
        buttonShape: Shape,
        fillColor: Int,
        maskColor: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            foreground = RippleDrawable(
                ColorStateList.valueOf(maskColor),
                ShapeDrawable(buttonShape).apply { paint.color = fillColor },
                ShapeDrawable(buttonShape).apply { paint.color = maskColor }
            )
        }
    }
}
