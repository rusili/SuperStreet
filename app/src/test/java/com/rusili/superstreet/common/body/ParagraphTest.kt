package com.rusili.superstreet.common.body

import com.rusili.superstreet.common.models.body.Paragraph
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

class ParagraphTest {

    @Test
    fun `Given valid ArticleHeader, When getViewType() is called, Then return 0`(){
        // Given
        val testString = "test"

        // When
        val result = Paragraph(0, testString)

        // Then
        result.getViewType() shouldEqual 1
    }
}
