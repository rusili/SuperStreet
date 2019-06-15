package com.rusili.superstreet.common.ui.actions

import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.setPadding
import com.rusili.superstreet.R
import com.rusili.superstreet.common.extensions.getDimen
import com.rusili.superstreet.common.ui.StyleableFrameLayout
import com.rusili.superstreet.jsoup.api.BASE_HTML

private const val STRING_SHARE_TYPE = "text/plain"
private const val STRING_SHARE_SUBJECT = "Sharing URL"
private const val STRING_SHARE_VIA = "Share via: "

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

    fun setShareLink(link: String) {
        findViewById<ImageView>(R.id.actionShare).setOnClickListener { shareLink(link) }
    }

    fun setFavoriteAction() {
        findViewById<ImageView>(R.id.actionFavorite).setOnClickListener {
            Toast.makeText(
                context,
                "TEST",
                Toast.LENGTH_SHORT
            ).show()
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

    private fun shareLink(link: String) {
        context.startActivity(
            Intent.createChooser(
                Intent(Intent.ACTION_SEND).apply {
                    setType(STRING_SHARE_TYPE)
                    putExtra(Intent.EXTRA_SUBJECT,
                        STRING_SHARE_SUBJECT
                    )
                    putExtra(Intent.EXTRA_TEXT, addDomainToLink(link))
                },
                STRING_SHARE_VIA
            )
        )
    }

    private fun addDomainToLink(link: String) =
        BASE_HTML.plus(link)

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
