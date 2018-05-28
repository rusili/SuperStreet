package com.rusili.superstreet.domain.models.flag

enum class Magazine(val value: String,
                    val href: String) {

    SUPER_STREET("Super Street", "/super-street-magazine/"),
    EUROPEAN_CAR("European Car", "/european-car-magazine/"),
    IMPORT_TUNER("Import Tuner", "/import-tuner-magazine/"),
    HONDA_TUNING("Honda Tuning", "/honda-tuning-magazine/"),

    ERROR("Error", "")
}