package com.rusili.superstreet.common.header

import com.rusili.superstreet.common.models.header.HeaderImage
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

class HeaderImageTest {

    @Test
    fun `Given sample image url w=659, h=439, q=80 , When image resizedto defaultSize, Return correct url`() {
        // Given
        val imageUrl = "http://image.superstreetonline.com/f/170287891+w660+h440+q80+re0+cr1+ar0+st0/2018-lexus-lc-500-hks-exhaust.jpg"
        val correctUrl = "http://image.superstreetonline.com/f/170287891+w600+h400+q80+re0+cr1+ar0+st0/2018-lexus-lc-500-hks-exhaust.jpg"
        val testSubject = HeaderImage("test", imageUrl)

        // When
        val result = testSubject.getDefaultSizeUrl()

        // Then
        result shouldEqual correctUrl
    }
}
