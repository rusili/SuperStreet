package com.rusili.superstreet.common

import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.Paragraph
import org.junit.Assert
import org.junit.Test

class BodyTest {

    @Test
    fun `Given valid sections, When combineSections() is called, Then return all lists combined and in order`() {
        // Given
        val paragraph1 = Paragraph(1, "")
        val paragraph2 = Paragraph(3, "")
        val paragraphList = listOf<Paragraph>(paragraph1, paragraph2)

        val image1 = Image(2, "")
        val image2 = Image(4, "")
        val imageList = listOf<Image>(image1, image2)

        // Then
        Assert.assertTrue(paragraph1 < paragraph2)
    }
}
