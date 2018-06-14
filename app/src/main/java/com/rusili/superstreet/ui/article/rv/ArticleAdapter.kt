package com.rusili.superstreet.ui.article.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.models.body.ArticleViewType
import com.rusili.superstreet.domain.models.body.AbstractBodyModel
import com.rusili.superstreet.ui.common.BaseViewHolder

class ArticleAdapter()
    : ListAdapter<AbstractBodyModel, RecyclerView.ViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                ArticleViewType.PARAGRAPH.viewType -> ParagraphViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.article_paragraph_viewholder, parent, false));
                ArticleViewType.IMAGE.viewType -> ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.article_image_viewholder, parent, false));
                ArticleViewType.IMAGE_GROUP.viewType -> ImageGroupViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.article_imagegroup_viewholder, parent, false));
                else -> ParagraphViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.article_paragraph_viewholder, parent, false));
            }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  position: Int) =
            (holder as BaseViewHolder<AbstractBodyModel>).bind(getItem(position))

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        val type = item.getViewType()

        return when (getItem(position).getViewType()) {
            ArticleViewType.PARAGRAPH.viewType -> ArticleViewType.PARAGRAPH.viewType
            ArticleViewType.IMAGE.viewType -> ArticleViewType.IMAGE.viewType
            ArticleViewType.IMAGE_GROUP.viewType -> ArticleViewType.IMAGE_GROUP.viewType
            else -> -1
        }
    }
}