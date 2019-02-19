package com.rusili.superstreet.common.models.body

// TODO: Convert to Sealed class
enum class ArticleViewType(val viewType: Int) {

    HEADER(0),
    PARAGRAPH(1),
    IMAGE(2),
    IMAGE_GROUP(3),
    GALLERY(4);
}
