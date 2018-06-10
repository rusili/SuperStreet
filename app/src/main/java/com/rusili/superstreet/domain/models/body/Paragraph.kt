package com.rusili.superstreet.domain.models.body

data class Paragraph(val id: Int,
                     val body: String) : BaseBody {

    override fun getViewType() = ArticleViewType.PARAGRAPH.viewType
}