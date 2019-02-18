package com.rusili.superstreet.domain

import com.rusili.superstreet.domain.models.body.ImageGallery
import org.junit.Assert
import org.junit.Test

class ImageGalleryModelTest {
    private val imageUrl = "http://image.superstreetonline.com/f/170287891+w659+h439+q80+re0+cr1+ar0+st0/2018-lexus-lc-500-hks-exhaust.jpg"

    @Test
    fun `Given sample image url w=659, h=439, q=80 , When image resizedto defaultSize, Return correct url`() {
        // Given
        val correctUrl = "http://image.superstreetonline.com/f/170287891+w720+h480+q80+re0+cr1+ar0+st0/2018-lexus-lc-500-hks-exhaust.jpg"
        val testSubject = ImageGallery(1, imageUrl)

        // When
        val testResult = testSubject.resizeToDefaultSize()

        // Then
        Assert.assertEquals(testResult, correctUrl)
    }

    @Test
    fun `Given sample image url w=659, h=439, q=80 , When image resizedto 1920x1080, Return correct url`() {
        // Given
        val correctUrl = "http://image.superstreetonline.com/f/170287891+w1920+h1280+q90+re0+cr1+ar0+st0/2018-lexus-lc-500-hks-exhaust.jpg"
        val testSubject = ImageGallery(1, imageUrl)

        // When
        val testResult = testSubject.resizeTo1920By1280()

        // Then
        Assert.assertEquals(testResult, correctUrl)
    }

    @Test
    fun `Given sample image url w=, h=, q=80, When image resizedto groupSize, Return correct url`() {
        // Given
        val correctUrl = "http://image.superstreetonline.com/f/170287891+w240+h160+q80+re0+cr1+ar0+st0/2018-lexus-lc-500-hks-exhaust.jpg"
        val testSubject = ImageGallery(1, imageUrl)

        // When
        val testResult = testSubject.resizeToGroupSize()

        // Then
        Assert.assertEquals(testResult, correctUrl)
    }
}