package com.rusili.superstreet.domain

import com.rusili.superstreet.domain.models.body.ImageGallery
import com.rusili.superstreet.domain.models.body.Paragraph
import org.junit.Assert
import org.junit.Test

class BodyModelTest {

    @Test
    fun `Test BodyModel combineLists function`() {
        // Given
        val paragraph1 = Paragraph(1, "")
        val paragraph2 = Paragraph(3, "")
        val paragraphList = listOf<Paragraph>(paragraph1, paragraph2)

        val image1 = ImageGallery(2, "")
        val image2 = ImageGallery(4, "")
        val imageList = listOf<ImageGallery>(image1, image2)

        // Then
        Assert.assertTrue(paragraph1 < paragraph2)
    }
}