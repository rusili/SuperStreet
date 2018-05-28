package com.rusili.superstreet.data

import com.rusili.superstreet.domain.models.flag.Magazine
import com.rusili.superstreet.domain.models.flag.Type

class FlagMapper {

    fun getMagazine(value: String): Magazine{
        return when (value) {
            "SuperStreetOnline Magazine" -> Magazine.SUPER_STREET
            "European Car Magazine" -> Magazine.EUROPEAN_CAR
            "Import Tuner Magazine" -> Magazine.IMPORT_TUNER
            "Honda Tuning Magazine" -> Magazine.HONDA_TUNING
            else -> Magazine.SUPER_STREET
        }
    }

    fun getType(value: String): Type{
        return when (value) {
            "features" -> Type.FEATURES
            "how to" -> Type.HOW_TO
            "event coverage" -> Type.EVENT_COVERAGE
            else -> Type.ERROR
        }
    }
}