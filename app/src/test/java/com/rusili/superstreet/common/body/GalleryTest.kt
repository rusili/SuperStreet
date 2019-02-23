package com.rusili.superstreet.common.body

import com.nhaarman.mockito_kotlin.mock
import com.rusili.superstreet.common.models.body.Gallery
import com.rusili.superstreet.common.models.body.Image
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

class GalleryTest {

    @Test
    fun `Given valid Gallery, When getViewType() is called, Then return 4`(){
        // Given
        val imageGallery = mock<Image>()

        // When
        val result = Gallery(images = setOf(imageGallery))

        // Then
        result.getViewType() shouldEqual 4
    }
}
