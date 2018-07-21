package com.rusili.superstreet.ui

import com.rusili.superstreet.ui.image.util.ImageSaver
import org.junit.Assert
import org.junit.Test

class ImageSaverTest {
    private val imageSaver = ImageSaver()

    @Test
    fun `Given complete image url, When getImageName is called, Return only the image name plus a timestamp`() {
        // Given
        val testSubject = "http://image.superstreetonline.com/f/170094988+w640+h427+q90+re0+cr1/97bmw-m-performance-parts-concept-.jpg"
        val correctName = "97bmw-m-performance-parts-concept-"

        // When
        val testName = imageSaver.parseImageName(testSubject)

        // Then
        Assert.assertEquals(testName, correctName)
    }
}