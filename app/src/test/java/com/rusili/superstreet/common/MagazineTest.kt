package com.rusili.superstreet.common

import com.rusili.superstreet.common.models.MAGAZINE_EUROPEANCAR_STRING
import com.rusili.superstreet.common.models.MAGAZINE_HONDATUNING_STRING
import com.rusili.superstreet.common.models.MAGAZINE_IMPORTTUNER_STRING
import com.rusili.superstreet.common.models.MAGAZINE_SUPERSTREET_STRING
import com.rusili.superstreet.common.models.Magazine
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

class MagazineTest {

    @Test
    fun `Given correct String values, When fromString() is called, Then return correct Magazine`(){
        // Given
        val superStreetString = MAGAZINE_SUPERSTREET_STRING
        val europeanCarString = MAGAZINE_EUROPEANCAR_STRING
        val importTunerString = MAGAZINE_IMPORTTUNER_STRING
        val hondaTuningString = MAGAZINE_HONDATUNING_STRING
        val errorString = "error"

        // When
        val resultSuperStreet = Magazine.fromString(superStreetString)
        val resultEuropeanCar = Magazine.fromString(europeanCarString)
        val resultImportTuner = Magazine.fromString(importTunerString)
        val resultHondaTuning = Magazine.fromString(hondaTuningString)
        val resultError = Magazine.fromString(errorString)

        // Then
        resultSuperStreet shouldBe Magazine.SUPERSTREET
        resultEuropeanCar shouldBe Magazine.EUROPEANCAR
        resultImportTuner shouldBe Magazine.IMPORTTUNER
        resultHondaTuning shouldBe Magazine.HONDATUNING
        resultError shouldBe Magazine.Error
    }
}