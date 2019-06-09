package com.rusili.superstreet.common.ui

import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.view.size
import com.rusili.superstreet.R

private const val STRING_SHARE_TYPE = "text/plain"
private const val STRING_SHARE_SUBJECT = "Sharing URL"
private const val STRING_SHARE_VIA = "Share via: "

class ActionsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : StyleableFrameLayout(context, attrs, defStyleAttr) {
    val allActionViews = setOf(actionFavorite, actionShare)
    lateinit var actionFavorite: ImageButton
        private set
    lateinit var actionShare: ImageButton
        private set

    var favoriteAction: (() -> Unit)? = null

    override fun getLayout(): Int? = R.layout.view_actions
    override fun getStyleable(): IntArray = R.styleable.ActionsView

    override fun TypedArray.setupViews() {
        actionFavorite = findViewById(R.id.actionFavorite)
        actionShare = findViewById(R.id.actionShare)

        selectSize()
    }

    private fun TypedArray.selectSize() {
        val smallDimens = resources.getDimension(R.dimen.action_button_height_small)
        val mediumDimens = resources.getDimension(R.dimen.action_button_height_medium)
        val largeDimens = resources.getDimension(R.dimen.action_button_height_large)

        getInt(R.styleable.ActionsView_size, -1)
            .takeIf { it != -1 }
            ?.let(ActionsViewSize.Companion::fromAttr)
            ?.let { size ->
                when (size) {
                    ActionsViewSize.SMALL -> applySize(smallDimens)
                    ActionsViewSize.MEDIUM -> applySize(mediumDimens)
                    ActionsViewSize.LARGE -> applySize(largeDimens)
                }
            }
    }

    private fun applySize(dimension: Float) {
        allActionViews.forEach { view ->
            view.layoutParams.apply {
                height = dimension.toInt()
                width = dimension.toInt()
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
}

enum class ActionsViewSize(val attr: Int) {
    SMALL(0),
    MEDIUM(1),
    LARGE(2);

    companion object {
        fun fromAttr(input: Int): ActionsViewSize =
            values().first { it.attr == input }
    }
}
