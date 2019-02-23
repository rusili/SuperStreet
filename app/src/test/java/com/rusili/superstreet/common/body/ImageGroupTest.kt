package com.rusili.superstreet.common.body

import com.nhaarman.mockito_kotlin.mock
import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.ImageGroup
import org.amshove.kluent.shouldEqual
import org.junit.Test

class ImageGroupTest {

    @Test
    fun `Given valid ImageGroup, When getViewType() is called, Then return 3`(){
        // Given
        val imageGallery = mock<Image>()

        // When
        val result = ImageGroup(0, listOf(imageGallery))

        // Then
        result.getViewType() shouldEqual 3
    }
}
