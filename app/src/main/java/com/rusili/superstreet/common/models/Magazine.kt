package com.rusili.superstreet.common.models

const val MAGAZINE_SUPERSTREET_VALUE = "Super Street"
const val MAGAZINE_EUROPEANCAR_VALUE = "European Car"
const val MAGAZINE_IMPORTTUNER_VALUE = "Import Tuner"
const val MAGAZINE_HONDATUNING_VALUE = "Honda Tuning"

const val MAGAZINE_SUPERSTREET_PATH = "/super-street-magazine/"
const val MAGAZINE_EUROPEANCAR_PATH = "/european-car-magazine/"
const val MAGAZINE_IMPORTTUNER_PATH = "/import-tuner-magazine/"
const val MAGAZINE_HONDATUNING_PATH = "/honda-tuning-magazine/"

const val MAGAZINE_SUPERSTREET_STRING = "SuperStreetOnline Magazine"
const val MAGAZINE_EUROPEANCAR_STRING = "European Car Magazine"
const val MAGAZINE_IMPORTTUNER_STRING = "Import Tuner Magazine"
const val MAGAZINE_HONDATUNING_STRING = "Honda Tuning Magazine"

enum class Magazine(
    val value: String,
    val path: String
) {
    SUPERSTREET(MAGAZINE_SUPERSTREET_VALUE, MAGAZINE_SUPERSTREET_PATH),
    EUROPEANCAR(MAGAZINE_EUROPEANCAR_VALUE, MAGAZINE_EUROPEANCAR_PATH),
    IMPORTTUNER(MAGAZINE_IMPORTTUNER_VALUE, MAGAZINE_IMPORTTUNER_PATH),
    HONDATUNING(MAGAZINE_HONDATUNING_VALUE, MAGAZINE_HONDATUNING_PATH),
    Error("Error", "");

    companion object {
        fun fromString(value: String): Magazine =
            when (value) {
                MAGAZINE_SUPERSTREET_STRING -> SUPERSTREET
                MAGAZINE_EUROPEANCAR_STRING -> EUROPEANCAR
                MAGAZINE_IMPORTTUNER_STRING -> IMPORTTUNER
                MAGAZINE_HONDATUNING_STRING -> HONDATUNING
                else -> Error
            }
    }
}
