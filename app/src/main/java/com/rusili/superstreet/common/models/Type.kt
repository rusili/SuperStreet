package com.rusili.superstreet.common.models

const val TYPE_FEATURE_STRING = "features"
const val TYPE_HOWTO_STRING = "how to"
const val TYPE_EVENT_STRING = "event coverage"

const val TYPE_FEATURE_VALUE = "FEATURE"
const val TYPE_HOWTO_VALUE = "How To"
const val TYPE_EVENT_VALUE = "Event"
const val TYPE_OTHER_VALUE = "Other"

const val TYPE_FEATURE_PATH = "/features/"
const val TYPE_HOWTO_PATH = "/how-to/"
const val TYPE_EVENT_PATH = "/event-coverage"
const val TYPE_OTHER_PATH = ""

enum class Type(
    val value: String,
    val path: String
) {
    FEATURE(TYPE_FEATURE_VALUE, TYPE_FEATURE_PATH),
    HOWTO(TYPE_HOWTO_VALUE, TYPE_HOWTO_PATH),
    EVENTCOVERAGE(TYPE_EVENT_VALUE, TYPE_EVENT_PATH),
    Other(TYPE_OTHER_VALUE, TYPE_OTHER_PATH);

    companion object {
        fun fromString(value: String): Type =
            when (value) {
                TYPE_FEATURE_STRING -> FEATURE
                TYPE_HOWTO_STRING -> HOWTO
                TYPE_EVENT_STRING -> EVENTCOVERAGE
                else -> Other
            }
    }
}
