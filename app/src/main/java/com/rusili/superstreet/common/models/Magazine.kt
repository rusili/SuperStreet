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

sealed class Magazine(
    val value: String,
    val path: String
) {
    object SuperStreet : Magazine(MAGAZINE_SUPERSTREET_VALUE, MAGAZINE_SUPERSTREET_PATH)
    object EuropeanCar : Magazine(MAGAZINE_EUROPEANCAR_VALUE, MAGAZINE_EUROPEANCAR_PATH)
    object ImportTuner : Magazine(MAGAZINE_IMPORTTUNER_VALUE, MAGAZINE_IMPORTTUNER_PATH)
    object HondaTuning : Magazine(MAGAZINE_HONDATUNING_VALUE, MAGAZINE_HONDATUNING_PATH)
    object Error : Magazine("Error", "")

    companion object {
        fun fromString(value: String): Magazine =
            when (value) {
                MAGAZINE_SUPERSTREET_STRING -> SuperStreet
                MAGAZINE_EUROPEANCAR_STRING -> EuropeanCar
                MAGAZINE_IMPORTTUNER_STRING -> ImportTuner
                MAGAZINE_HONDATUNING_STRING -> HondaTuning
                else -> Error
            }
    }
}
