package com.rusili.superstreet.article.ui.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.rusili.superstreet.R
import com.rusili.superstreet.common.models.body.AbstractBodyModel
import com.rusili.superstreet.common.models.body.ArticleViewType
import com.rusili.superstreet.common.models.body.ImageGallery
import com.rusili.superstreet.common.models.body.ImageSize
import com.rusili.superstreet.common.ui.BaseViewHolder

class ArticleAdapter(
    private val onClick: (View, ImageGallery, ImageSize) -> Unit,
    private val glide: RequestManager
) : ListAdapter<AbstractBodyModel, RecyclerView.ViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ArticleViewType.Header.viewType -> HeaderViewHolder(inflate(parent, R.layout.article_header_viewholder), glide)
            ArticleViewType.Paragraph.viewType -> ParagraphViewHolder(inflate(parent, R.layout.article_paragraph_viewholder))
            ArticleViewType.Image.viewType -> ImageViewHolder(inflate(parent, R.layout.article_image_viewholder), onClick, glide)
            ArticleViewType.ImageGroup.viewType -> ImageGroupViewHolder(inflate(parent, R.layout.article_imagegroup_viewholder), onClick, glide)
            else -> ParagraphViewHolder(inflate(parent, R.layout.article_paragraph_viewholder));
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as BaseViewHolder<AbstractBodyModel>).bind(getItem(position))

    override fun getItemViewType(position: Int) =
        when (getItem(position).getViewType()) {
            ArticleViewType.Header.viewType -> ArticleViewType.Header.viewType
            ArticleViewType.Paragraph.viewType -> ArticleViewType.Paragraph.viewType
            ArticleViewType.Image.viewType -> ArticleViewType.Image.viewType
            ArticleViewType.ImageGroup.viewType -> ArticleViewType.ImageGroup.viewType
            else -> -1
        }

    private fun inflate(parent: ViewGroup, layout: Int) =
        LayoutInflater.from(parent.context).inflate(layout, parent, false)
}