package com.rusili.superstreet.common.ui.actions

import android.content.Context
import android.content.res.TypedArray
import android.media.Image
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.setPadding
import com.rusili.superstreet.R
import com.rusili.superstreet.common.extensions.getDimen
import com.rusili.superstreet.common.extensions.shareLink
import com.rusili.superstreet.common.ui.StyleableFrameLayout

class ActionsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : StyleableFrameLayout(context, attrs, defStyleAttr) {
    private lateinit var actionFavorite: ImageButton
    private lateinit var actionShare: ImageButton
    private lateinit var allActionViews: List<ImageButton>

    override fun getLayout(): Int? = R.layout.view_actions
    override fun getStyleable(): IntArray = R.styleable.ActionsView

    override fun TypedArray.setupViews() {
        actionFavorite = findViewById(R.id.actionFavorite)
        actionShare = findViewById(R.id.actionShare)
        allActionViews = listOf(actionFavorite, actionShare)

        setSize()
    }

    fun setFavorite(isFavorite: Boolean) {
        actionFavorite.isSelected = isFavorite
    }

    fun setShareLink(link: String) {
        actionShare.setOnClickListener {
            context.shareLink(link)
        }
    }

    fun setFavoriteAction(callback: (isSelected: Boolean) -> Unit) {
        actionFavorite.setOnClickListener {
            callback(it.isSelected)
            it.isSelected = !it.isSelected
        }
    }

    private fun TypedArray.setSize() {
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
        val params = LinearLayout.LayoutParams(style.length, style.length)

        allActionViews.forEach { view ->
            view.apply {
                layoutParams = params
                setPadding(style.padding)
            }
        }
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
