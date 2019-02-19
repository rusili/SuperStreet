package com.rusili.superstreet.common.models

// TODO: Convert to Sealed class

enum class Type(
    val value: String,
    val href: String
) {
    FEATURES("Feature", "/features/"),
    HOW_TO("How To", "/how-to/"),
    EVENT_COVERAGE("Event", "/event-coverage/"),
    OTHER("Other", "");

    companion object {
        fun fromString(value: String) =
            when (value) {
                "features" -> FEATURES
                "how to" -> HOW_TO
                "event coverage" -> EVENT_COVERAGE
                else -> OTHER
            }
    }
}
