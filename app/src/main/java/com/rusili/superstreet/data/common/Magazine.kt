package com.rusili.superstreet.data.common

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
                "SuperStreetOnline Magazine" -> Magazine.SUPER_STREET
                "European Car Magazine" -> Magazine.EUROPEAN_CAR
                "Import Tuner Magazine" -> Magazine.IMPORT_TUNER
                "Honda Tuning Magazine" -> Magazine.HONDA_TUNING
                else -> Magazine.SUPER_STREET
            }
    }
}
