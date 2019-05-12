package com.rusili.superstreet.common.body

import com.rusili.superstreet.common.models.ImageUrl
import com.rusili.superstreet.common.models.body.Image
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

class ImageTest {
    private val imageUrl = "http://image.superstreetonline.com/f/170287891+w659+h439+q80+re0+cr1+ar0+st0/2018-lexus-lc-500-hks-exhaust.jpg"

    @Test
    fun `Given valid Image, When getViewType() is called, Then return 2`(){
        // Given
        val url = "test"

        // When
        val result = Image(0, ImageUrl(url))

        // Then
        result.getViewType() shouldEqual 2
    }

    @Test
    fun `Given sample image url w=659, h=439, q=80 , When image resizedto defaultSize, Return correct url`() {
        // Given
        val correctUrl = "http://image.superstreetonline.com/f/170287891+w720+h480+q80+re0+cr1+ar0+st0/2018-lexus-lc-500-hks-exhaust.jpg"
        val testSubject = Image(1, ImageUrl(imageUrl))

        // When
        val testResult = testSubject.getDefaultSizeUrl()

        // Then
        testResult shouldEqual correctUrl
    }

    @Test
    fun `Given sample image url w=659, h=439, q=80 , When image resizedto 1920x1080, Return correct url`() {
        // Given
        val correctUrl = "http://image.superstreetonline.com/f/170287891+w1920+h1280+q90+re0+cr1+ar0+st0/2018-lexus-lc-500-hks-exhaust.jpg"
        val testSubject = Image(1, ImageUrl(imageUrl))

        // When
        val testResult = testSubject.getHighResUrl()

        // Then
        testResult shouldEqual correctUrl
    }

    @Test
    fun `Given sample image url w=659, h=439, q=80, When image resizedto groupSize, Return correct url`() {
        // Given
        val correctUrl = "http://image.superstreetonline.com/f/170287891+w240+h160+q80+re0+cr1+ar0+st0/2018-lexus-lc-500-hks-exhaust.jpg"
        val testSubject = Image(1, ImageUrl(imageUrl))

        // When
        val testResult = testSubject.getGroupSizeUrl()

        // Then
        testResult shouldEqual correctUrl
    }
}