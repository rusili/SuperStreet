package com.rusili.superstreet.common.models

sealed class Magazine(
    val value: String,
    val href: String
) {
    object SuperStreet : Magazine("Super Street", "/super-street-magazine/")
    object EuropeanCar : Magazine("European Car", "/european-car-magazine/")
    object ImportTuner : Magazine("Import Tuner", "/import-tuner-magazine/")
    object HondaTuning : Magazine("Honda Tuning", "/honda-tuning-magazine/")
    object Error : Magazine("Error", "")

    companion object {
        fun fromString(value: String): Magazine =
            when (value) {
                "SuperStreetOnline Magazine" -> SuperStreet
                "European Car Magazine" -> EuropeanCar
                "Import Tuner Magazine" -> ImportTuner
                "Honda Tuning Magazine" -> HondaTuning
                else -> Error
            }
    }
}
