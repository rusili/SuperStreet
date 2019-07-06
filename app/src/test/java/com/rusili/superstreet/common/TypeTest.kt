package com.rusili.superstreet.common

import com.rusili.superstreet.common.models.TYPE_EVENT_STRING
import com.rusili.superstreet.common.models.TYPE_FEATURE_STRING
import com.rusili.superstreet.common.models.TYPE_HOWTO_STRING
import com.rusili.superstreet.common.models.Type
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test

class TypeTest {

    @Test
    fun `Given correct String values, When fromString() is called, Then return correct Type`(){
        // Given
        val featureString = TYPE_FEATURE_STRING
        val howToString = TYPE_HOWTO_STRING
        val eventString = TYPE_EVENT_STRING
        val otherString = "anything"

        // When
        val resultFeature = Type.fromString(featureString)
        val resultHowTo = Type.fromString(howToString)
        val resultEvent = Type.fromString(eventString)
        val resultOther = Type.fromString(otherString)

        // Then
        resultFeature shouldBe Type.FEATURE
        resultHowTo shouldBe Type.HOWTO
        resultEvent shouldBe Type.EVENTCOVERAGE
        resultOther shouldBe Type.Other
    }
}
