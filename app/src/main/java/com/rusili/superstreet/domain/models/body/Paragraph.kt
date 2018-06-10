package com.rusili.superstreet.domain.models.body

data class Paragraph(override val id: Int,
                     val body: String) : BaseBody2(id) {

    override fun getViewType() = ArticleViewType.PARAGRAPH.viewType
}