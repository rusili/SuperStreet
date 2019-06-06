package com.rusili.superstreet.common.ui

import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import com.rusili.superstreet.R
import com.rusili.superstreet.common.base.StyleableFrameLayout

private const val STRING_SHARE_TYPE = "text/plain"
private const val STRING_SHARE_SUBJECT = "Sharing URL"
private const val STRING_SHARE_VIA = "Share via: "

class ActionsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : StyleableFrameLayout(context, attrs, defStyleAttr) {
    var favoriteAction: (() -> Unit)? = null

    override fun getLayout(): Int? = R.layout.view_actions

    override fun TypedArray.setupViews() {
        findViewById<ImageView>(R.id.actionFavorite).setOnClickListener { favoriteAction?.invoke() }
    }

    fun setShareLink(link: String) {
        findViewById<ImageView>(R.id.actionShare).setOnClickListener { shareLink(link) }
    }

    private fun shareLink(link: String) {
        context.startActivity(
            Intent.createChooser(
                Intent(Intent.ACTION_SEND).apply {
                    setType(STRING_SHARE_TYPE)
                    putExtra(Intent.EXTRA_SUBJECT, STRING_SHARE_SUBJECT)
                    putExtra(Intent.EXTRA_TEXT, link)
                },
                STRING_SHARE_VIA
            )
        )
    }
}
