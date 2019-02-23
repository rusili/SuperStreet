package com.rusili.superstreet.common.body

import com.rusili.superstreet.common.models.body.ImageSize
import org.amshove.kluent.shouldEqual
import org.junit.Test

class ImageSizeTest {

    @Test
    fun `Given ImageSize enum, When values() is called, Then return correct values`(){
        // Given
        val default = "DEFAULT"
        val group = "GROUP"
        val large = "LARGE"

        // When
        val result = ImageSize.values()

        // Then
        result.size shouldEqual 3
        result[0].name shouldEqual default
        result[1].name shouldEqual group
        result[2].name shouldEqual large
    }
}
