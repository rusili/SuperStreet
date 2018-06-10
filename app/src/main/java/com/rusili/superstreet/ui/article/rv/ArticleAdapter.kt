package com.rusili.superstreet.ui.article.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.article.ArticleFullModel
import com.rusili.superstreet.domain.models.body.ArticleViewType
import com.rusili.superstreet.domain.models.body.BaseBody
import com.rusili.superstreet.ui.common.BaseViewHolder

class ArticleAdapter(private val bodyItemList: List<BaseBody>,
                     private val layoutInflater: LayoutInflater)
    : ListAdapter<ArticleFullModel, RecyclerView.ViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                ArticleViewType.PARAGRAPH.viewType -> ParagraphViewHolder(layoutInflater.inflate(R.layout.fragment_list, parent, false));
                ArticleViewType.IMAGE.viewType -> ImageViewHolder(layoutInflater.inflate(R.layout.fragment_list, parent, false));
                ArticleViewType.IMAGE_GROUP.viewType -> ImageGroupViewHolder(layoutInflater.inflate(R.layout.fragment_list, parent, false));
                else -> ParagraphViewHolder(layoutInflater.inflate(R.layout.fragment_list, parent, false));
            }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  position: Int) =
            (holder as BaseViewHolder<BaseBody>).bind(bodyItemList[position])

    override fun getItemViewType(position: Int) = bodyItemList[position].getViewType()
}