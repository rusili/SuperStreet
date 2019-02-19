package com.rusili.superstreet.previewlist.ui.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.bumptech.glide.RequestManager
import com.rusili.superstreet.R
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import com.rusili.superstreet.previewlist.domain.CardSize
import com.rusili.superstreet.common.models.header.Title
import com.rusili.superstreet.previewlist.DateHelper

class PreviewListAdapter(private val onClick: (View, Title) -> Unit,
                         private val glide: RequestManager,
                         private val dateHelper: DateHelper
)
    : PagedListAdapter<ArticlePreviewModel, PreviewViewHolder>(PreviewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PreviewViewHolder =
            when (viewType) {
                CardSize.Large.viewType -> PreviewViewHolder(inflate(parent, R.layout.preview_viewholder_large), onClick, glide, dateHelper)
                CardSize.Small.viewType -> PreviewViewHolder(inflate(parent, R.layout.preview_viewholder_small), onClick, glide, dateHelper)
                else -> PreviewViewHolder(inflate(parent, R.layout.preview_viewholder_small), onClick, glide, dateHelper)
            }

    override fun onBindViewHolder(holder: PreviewViewHolder,
                                  position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemViewType(position: Int) =
            when (getItem(position)?.getViewType()) {
                CardSize.Large.viewType -> CardSize.Large.viewType
                CardSize.Small.viewType -> CardSize.Small.viewType
                else -> -1
            }

    private fun inflate(parent: ViewGroup,
                        layout: Int) =
            LayoutInflater.from(parent.context).inflate(layout, parent, false)
}