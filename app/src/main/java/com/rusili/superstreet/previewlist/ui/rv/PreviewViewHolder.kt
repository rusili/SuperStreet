package com.rusili.superstreet.previewlist.ui.rv

import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.view.ViewCompat
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rusili.superstreet.home.HomeNavigator
import com.rusili.superstreet.R
import com.rusili.superstreet.common.base.BaseActivity
import com.rusili.superstreet.common.base.BaseViewHolder
import com.rusili.superstreet.common.models.BaseArticleModel
import com.rusili.superstreet.common.models.header.HeaderImage.Companion.HEADER_IMAGE_HEIGHT
import com.rusili.superstreet.common.models.header.HeaderImage.Companion.HEADER_IMAGE_WIDTH
import com.rusili.superstreet.common.ui.actions.HasActionsView
import com.rusili.superstreet.previewlist.DateHelper
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import com.rusili.superstreet.previewlist.ui.PreviewListListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewholder_preview_large.*
import java.util.Date

private val crossFadeTransition = DrawableTransitionOptions.withCrossFade()

class PreviewViewHolder(
    override val containerView: View,
    private val navigator: HomeNavigator,
    private val glide: RequestManager,
    private val dateHelper: DateHelper,
    private val listener: PreviewListListener
) : BaseViewHolder<ArticlePreviewModel>(containerView), LayoutContainer, HasActionsView {

    override fun bind(model: ArticlePreviewModel) {
        if (adapterPosition > 2) {
            animate()
        }

        setImage(model)
        setText(model)
        setActionsView(model)

        previewBackground.setOnClickListener {
            navigator.goToArticle(
                view = previewThumbnail,
                model = model
            )
        }
    }

    override fun setActionsView(model: BaseArticleModel) {
        previewActionsView.apply {
            setFavorite(model.isFavorite)
            setShareLink(model.header.title.href)
            setFavoriteAction { isSelected ->
                listener.setFavorite(model, isSelected)
            }
        }
    }

    private fun animate() {
        itemView.startAnimation(
            AnimationUtils.loadAnimation(
                itemView.context,
                R.anim.item_animation_fall_down
            )
        )
    }

    private fun setImage(model: ArticlePreviewModel) {
        ViewCompat.setTransitionName(previewThumbnail, model.header.headerImage.title)
        ViewCompat.setTransitionName(previewLayout, model.header.headerImage.getDefaultSizeUrl())

        glide.load(model.header.headerImage.getDefaultSizeUrl())
            .transition(crossFadeTransition)
            .override(HEADER_IMAGE_WIDTH, HEADER_IMAGE_HEIGHT)
            .into(previewThumbnail)
    }

    private fun setText(model: ArticlePreviewModel) {
        previewTitle.text = model.header.title.value
        //        previewDesc.text = model.header.desc
        previewMag.text = model.flag.magazine.value
        previewType.text = model.flag.type.value
        previewAuthorTimestamp.text = dateHelper.getDateDifferenceString(
            todaysDate = Date(),
            articleDate = model.footer.date
        ) + " - " + model.footer.author.value
    }
}
