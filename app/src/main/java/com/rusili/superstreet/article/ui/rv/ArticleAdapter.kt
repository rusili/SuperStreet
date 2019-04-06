package com.rusili.superstreet.article.ui.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.rusili.superstreet.R
import com.rusili.superstreet.common.base.BaseViewHolder
import com.rusili.superstreet.common.extensions.fadeIn
import com.rusili.superstreet.common.models.body.AbstractBodyModel
import com.rusili.superstreet.common.models.body.ArticleViewType
import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.ImageSize

class ArticleAdapter(
    private val onClick: (View, Image, ImageSize) -> Unit,
    private val glide: RequestManager
) : ListAdapter<AbstractBodyModel, RecyclerView.ViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ArticleViewType.Paragraph.viewType -> ParagraphViewHolder(parent.inflate(R.layout.viewholder_article_paragraph))
            ArticleViewType.Image.viewType -> ImageViewHolder(parent.inflate(R.layout.viewholder_article_image), onClick, glide)
            ArticleViewType.ImageGroup.viewType -> ImageGroupViewHolder(parent.inflate(R.layout.viewholder_article_imagegroup), onClick, glide)
            else -> ParagraphViewHolder(parent.inflate(R.layout.viewholder_article_paragraph))
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            (holder as BaseViewHolder<AbstractBodyModel>).apply {
                bind(it)
            }
        }
    }

    override fun getItemViewType(position: Int) =
        when (getItem(position).getViewType()) {
            ArticleViewType.Paragraph.viewType -> ArticleViewType.Paragraph.viewType
            ArticleViewType.Image.viewType -> ArticleViewType.Image.viewType
            ArticleViewType.ImageGroup.viewType -> ArticleViewType.ImageGroup.viewType
            else -> -1
        }

    private fun ViewGroup.inflate(layout: Int) =
        LayoutInflater.from(context).inflate(layout, this, false)
}
