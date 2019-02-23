package com.rusili.superstreet.common.models.body

sealed class ArticleViewType(val viewType: Int) {

    object Header : ArticleViewType(0)
    object Paragraph : ArticleViewType(1)
    object Image : ArticleViewType(2)
    object ImageGroup : ArticleViewType(3)
    object Gallery : ArticleViewType(4)
}
