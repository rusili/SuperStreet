package com.rusili.superstreet.ui.list.rv

import android.support.v7.recyclerview.extensions.ListAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.list.ArticlePreviewModel

class PreviewListAdapter()
    : ListAdapter<ArticlePreviewModel, PreviewViewHolder>(PreviewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PreviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.preview_viewholder, parent, false)
        return PreviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: PreviewViewHolder,
                                  position: Int) {
        holder.bind(getItem(position))
    }
}