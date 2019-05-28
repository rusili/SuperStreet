package com.rusili.superstreet.common.models

data class Flag(
    val magazine: Magazine,
    val type: Type
) {

    fun isValid() =
        magazine != Magazine.Error
}
