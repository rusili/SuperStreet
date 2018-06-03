package com.rusili.superstreet.domain.models.flag

enum class Type(val value: String,
                val href: String) {

    FEATURES("Features", "/features/"),
    HOW_TO("How To", "/how-to/"),
    EVENT_COVERAGE("Event Coverage", "/event-coverage/"),

    OTHER("Other", "")
}