package com.rusili.superstreet.common.ui

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.ImageView
import com.rusili.superstreet.R
import com.rusili.superstreet.common.base.StyleableFrameLayout

class ActionsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : StyleableFrameLayout(context, attrs, defStyleAttr) {
    var favoriteAction: (() -> Unit)? = null
    var shareAction: (() -> Unit)? = null

    override fun getLayout(): Int? = R.layout.view_actions

    override fun TypedArray.setupViews() {
        findViewById<ImageView>(R.id.actionFavorite).setOnClickListener { favoriteAction?.invoke() }
        findViewById<ImageView>(R.id.actionShare).setOnClickListener { shareAction?.invoke() }
    }
}
