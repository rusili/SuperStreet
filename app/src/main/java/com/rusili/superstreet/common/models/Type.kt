package com.rusili.superstreet.common.models

sealed class Type(
    val value: String,
    val href: String
) {
    object Features : Type("Feature", "/features/")
    object HowTo : Type("How To", "/how-to/")
    object EventCoverage : Type("Event", "/event-coverage/")
    object Other : Type("Other", "")

    companion object {
        fun fromString(value: String): Type =
            when (value) {
                "features" -> Features
                "how to" -> HowTo
                "event coverage" -> EventCoverage
                else -> Other
            }
    }
}
