package com.rusili.superstreet.previewlist.domain

sealed class CardSize(val viewType: Int) {

    object Small : CardSize(0)
    object Large : CardSize(1)
}
