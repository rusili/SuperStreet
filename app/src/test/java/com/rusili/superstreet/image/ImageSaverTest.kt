package com.rusili.superstreet.image

import com.rusili.superstreet.image.extensions.parseImageName
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

class ImageSaverTest {

    @Test
    fun `Given complete image url, When getImageName is called, Return only the image name`() {
        // Given
        val testSubject = "http://image.superstreetonline.com/f/170094988+w640+h427+q90+re0+cr1/97bmw-m-performance-parts-concept-.jpg"
        val correctName = "97bmw-m-performance-parts-concept-"

        // When
        val testName = testSubject.parseImageName()

        // Then
        testName shouldEqual correctName
    }

    @Test
    fun `Given invalid image url, When getImageName is called, Return the original string`() {
        // Given
        val testSubject = "170094988+w640+h427+q90+re0+cr197bmw-m-performance-parts-concept-"
        val correctName = "170094988+w640+h427+q90+re0+cr197bmw-m-performance-parts-concept-"

        // When
        val testName = testSubject.parseImageName()

        // Then
        testName shouldEqual correctName
    }
}
