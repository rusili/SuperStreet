package com.rusili.superstreet.common.models.header

data class Title(
    val value: String,
    val href: String
) {

    fun isValid() =
        value.isNotBlank() && href.isNotBlank()
}
