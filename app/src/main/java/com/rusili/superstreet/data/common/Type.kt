package com.rusili.superstreet.data.common

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
                "features" -> Type.FEATURES
                "how to" -> Type.HOW_TO
                "event coverage" -> Type.EVENT_COVERAGE
                else -> Type.OTHER
            }
    }
}
