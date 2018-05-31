package com.rusili.superstreet.ui.list.rv

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import com.rusili.superstreet.domain.models.header.Title

class PreviewListAdapter(private val onClick: (Title) -> Unit)
    : ListAdapter<ArticlePreviewModel, PreviewViewHolder>(PreviewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PreviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.preview_viewholder, parent, false)
        return PreviewViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: PreviewViewHolder,
                                  position: Int) {
        holder.bind(getItem(position))
    }
}