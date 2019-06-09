package com.rusili.superstreet.common.ui

import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.setPadding
import com.rusili.superstreet.R
import com.rusili.superstreet.common.extensions.getDimen

private const val STRING_SHARE_TYPE = "text/plain"
private const val STRING_SHARE_SUBJECT = "Sharing URL"
private const val STRING_SHARE_VIA = "Share via: "

class ActionsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : StyleableFrameLayout(context, attrs, defStyleAttr) {
    lateinit var actionFavorite: ImageButton
        private set
    lateinit var actionShare: ImageButton
        private set
    lateinit var allActionViews: List<ImageButton>
        private set

    var favoriteAction: (() -> Unit)? = null

    override fun getLayout(): Int? = R.layout.view_actions
    override fun getStyleable(): IntArray = R.styleable.ActionsView

    override fun TypedArray.setupViews() {
        actionFavorite = findViewById(R.id.actionFavorite)
        actionShare = findViewById(R.id.actionShare)
        allActionViews = listOf(actionFavorite, actionShare)

        selectSize()
    }

    private fun TypedArray.selectSize() {
        val smallStyle = ViewStyle(
            length = getDimen(R.dimen.action_button_height_small),
            padding = getDimen(R.dimen.action_button_padding_small)
        )
        val mediumStyle = ViewStyle(
            length = getDimen(R.dimen.action_button_height_medium),
            padding = getDimen(R.dimen.action_button_padding_medium)
        )
        val largeStyle = ViewStyle(
            length = getDimen(R.dimen.action_button_height_large),
            padding = getDimen(R.dimen.action_button_padding_large)
        )

        getInt(R.styleable.ActionsView_size, -1)
            .takeIf { it != -1 }
            ?.let(ViewSize.Companion::fromAttr)
            ?.let { size ->
                when (size) {
                    ViewSize.SMALL -> applySize(smallStyle)
                    ViewSize.MEDIUM -> applySize(mediumStyle)
                    ViewSize.LARGE -> applySize(largeStyle)
                }
            }
    }

    private fun applySize(style: ViewStyle) {
        allActionViews.forEach { view ->
            view.apply {
                layoutParams = LinearLayout.LayoutParams(style.length, style.length)
                setPadding(style.padding)
            }
        }
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

    private enum class ViewSize(val attr: Int) {
        SMALL(0),
        MEDIUM(1),
        LARGE(2);

        companion object {
            fun fromAttr(input: Int): ViewSize =
                values().first { it.attr == input }
        }
    }

    private data class ViewStyle(
        val length: Int,
        val padding: Int
    )
}
