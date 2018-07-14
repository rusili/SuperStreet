package com.rusili.superstreet.ui.list.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import com.rusili.superstreet.domain.models.header.Title

class PreviewListAdapter(private val onClick: (View, Title) -> Unit,
                         private val glide: RequestManager)
    : ListAdapter<ArticlePreviewModel, PreviewViewHolder>(PreviewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PreviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.preview_viewholder, parent, false)
        return PreviewViewHolder(view, onClick, glide)
    }

    override fun onBindViewHolder(holder: PreviewViewHolder,
                                  position: Int) {
        holder.bind(getItem(position))
    }
}