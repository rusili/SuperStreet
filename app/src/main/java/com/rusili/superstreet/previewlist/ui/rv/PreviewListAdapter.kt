package com.rusili.superstreet.previewlist.ui.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.paging.PagedListAdapter
import com.bumptech.glide.RequestManager
import com.rusili.superstreet.R
import com.rusili.superstreet.common.models.Header
import com.rusili.superstreet.previewlist.DateHelper
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import com.rusili.superstreet.previewlist.domain.CardSize

class PreviewListAdapter(
    private val onClick: (View, Header) -> Unit,
    private val glide: RequestManager,
    private val dateHelper: DateHelper
) : PagedListAdapter<ArticlePreviewModel, PreviewViewHolder>(PreviewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewViewHolder =
        when (viewType) {
            CardSize.Large.viewType -> PreviewViewHolder(parent.inflate(R.layout.viewholder_preview_large), onClick, glide, dateHelper)
            CardSize.Small.viewType -> PreviewViewHolder(parent.inflate(R.layout.viewholder_preview_small), onClick, glide, dateHelper)
            else -> PreviewViewHolder(parent.inflate(R.layout.viewholder_preview_small), onClick, glide, dateHelper)
        }

    override fun onBindViewHolder(holder: PreviewViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }

    override fun getItemViewType(position: Int) =
        when (getItem(position)?.getViewType()) {
            CardSize.Large.viewType -> CardSize.Large.viewType
            CardSize.Small.viewType -> CardSize.Small.viewType
            else -> -1
        }

    private fun ViewGroup.inflate(layout: Int) =
        LayoutInflater.from(context).inflate(layout, this, false)
}
