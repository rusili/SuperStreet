package com.rusili.superstreet

import com.rusili.superstreet.ui.util.ImageNameHelper
import org.junit.Assert
import org.junit.Test
import java.util.*

class ImageNameHelperTest {
    private val imageNameHelper = ImageNameHelper()

    @Test
    fun `Given complete image url, When getImageName is called, Return only the image name plus a timestamp`() {
        // Given
        val testSubject = "http://image.superstreetonline.com/f/170094988+w640+h427+q90+re0+cr1/97bmw-m-performance-parts-concept-.jpg"
        val correctName = "97bmw-m-performance-parts-concept-" + Date().time / 1000

        // When
        val testName = imageNameHelper.getImageName(testSubject)

        // Then
        Assert.assertEquals(testName, correctName)
    }
}