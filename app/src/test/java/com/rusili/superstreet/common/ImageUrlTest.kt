package com.rusili.superstreet.common

import com.rusili.superstreet.common.models.ImageUrl
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

class ImageUrlTest {

    @Test
    fun `Given valid url, When resize() is called, then return valid url`() {
        // Given
        val url = "http://assets.superstreetonline.com/uploads/5/2019/05/Corvette-Exo-Car-02.jpg?width=660&height=440"
        val expected = "http://assets.superstreetonline.com/uploads/5/2019/05/Corvette-Exo-Car-02.jpg?width=800&height=600"
        val imageUrl = ImageUrl(url)

        // When
        val result = imageUrl.resize(800, 600)

        // Then
        result shouldEqual expected
    }
}
