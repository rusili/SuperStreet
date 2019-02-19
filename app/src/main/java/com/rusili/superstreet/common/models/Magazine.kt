package com.rusili.superstreet.common.models

enum class Magazine(
    val value: String,
    val href: String
) {
    SUPER_STREET("Super Street", "/super-street-magazine/"),
    EUROPEAN_CAR("European Car", "/european-car-magazine/"),
    IMPORT_TUNER("Import Tuner", "/import-tuner-magazine/"),
    HONDA_TUNING("Honda Tuning", "/honda-tuning-magazine/"),
    ERROR("Error", "");

    companion object {
        fun fromString(value: String) =
            when (value) {
                "SuperStreetOnline Magazine" -> SUPER_STREET
                "European Car Magazine" -> EUROPEAN_CAR
                "Import Tuner Magazine" -> IMPORT_TUNER
                "Honda Tuning Magazine" -> HONDA_TUNING
                else -> SUPER_STREET
            }
    }
}
