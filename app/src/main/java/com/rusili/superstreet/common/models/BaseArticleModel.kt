package com.rusili.superstreet.common.models

abstract class BaseArticleModel {
    abstract val flag: Flag
    abstract val header: Header
    abstract val footer: Footer
    abstract var isFavorite: Boolean
}
