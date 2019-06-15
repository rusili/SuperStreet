package com.rusili.superstreet.common.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.Shape
import android.os.Build
import android.util.AttributeSet
import android.widget.ImageButton
import androidx.annotation.ColorInt
import androidx.core.content.res.getColorOrThrow
import androidx.core.content.withStyledAttributes
import com.rusili.superstreet.R

@SuppressLint("ResourceAsColor")
private const @ColorInt val MASK_COLOR_DEFAULT: Int = R.color.black_10_1000

class ShapedRippleImageButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageButton(context, attrs, defStyleAttr) {

    init {
        context.withStyledAttributes(
            attrs,
            R.styleable.ShapedRippleImageButton,
            defStyleAttr,
            0
        ) {
            setupViews()
        }
    }

    private fun TypedArray.setupViews() {
        val buttonShape = OvalShape()

        @ColorInt val fillColor = resources.getColor(android.R.color.transparent)
        @ColorInt val maskColor = getColor(
            R.styleable.ShapedRippleImageButton_ripple_color,
            MASK_COLOR_DEFAULT
        )

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
