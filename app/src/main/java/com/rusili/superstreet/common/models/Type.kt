package com.rusili.superstreet.common.models

const val TYPE_FEATURE_STRING = "feature"
const val TYPE_HOWTO_STRING = "how to"
const val TYPE_EVENT_STRING = "event coverage"

const val TYPE_FEATURE_VALUE = "Feature"
const val TYPE_HOWTO_VALUE = "How To"
const val TYPE_EVENT_VALUE = "Event"
const val TYPE_OTHER_VALUE = "Other"

const val TYPE_FEATURE_PATH = "/features/"
const val TYPE_HOWTO_PATH = "/how-to/"
const val TYPE_EVENT_PATH = "/event-coverage"
const val TYPE_OTHER_PATH = ""

sealed class Type(
    val value: String,
    val path: String
) {
    object Feature : Type(TYPE_FEATURE_VALUE, TYPE_FEATURE_PATH)
    object HowTo : Type(TYPE_HOWTO_VALUE, TYPE_HOWTO_PATH)
    object EventCoverage : Type(TYPE_EVENT_VALUE, TYPE_EVENT_PATH)
    object Other : Type(TYPE_OTHER_VALUE, TYPE_OTHER_PATH)

    companion object {
        fun fromString(value: String): Type =
            when (value) {
                TYPE_FEATURE_STRING -> Feature
                TYPE_HOWTO_STRING -> HowTo
                TYPE_EVENT_STRING -> EventCoverage
                else -> Other
            }
    }
}
