package com.rusili.superstreet.common.body

import com.rusili.superstreet.common.models.body.Paragraph
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class AbstractBodyModelTest {

    @Test
    fun `Test AbstractBodyModel compareTo function, compare Less than`() {
        // Given
        val paragraph1 = Paragraph(1, "")
        val paragraph2 = Paragraph(2, "")

        // Then
        assertTrue(paragraph1 < paragraph2)
    }

    @Test
    fun `Test AbstractBodyModel compareTo function, compare Greater than`() {
        // Given
        val paragraph1 = Paragraph(1, "")
        val paragraph2 = Paragraph(2, "")

        // Then
        assertFalse(paragraph2 < paragraph1)
    }

    @Test
    fun `Test AbstractBodyModel compareTo function, compare Equals than`() {
        // Given
        val paragraph1 = Paragraph(1, "")
        val paragraph2 = Paragraph(1, "")

        // Then
        paragraph1 shouldEqual paragraph2
    }
}
