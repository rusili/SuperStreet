package com.rusili.superstreet

import com.rusili.superstreet.data.article.ArticleMapper
import com.rusili.superstreet.data.util.FlagMapper
import com.rusili.superstreet.domain.article.ArticleFullModel
import com.rusili.superstreet.domain.models.body.Paragraph
import org.jsoup.Jsoup
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.File

class AbstractBodyModelTest {

    @Test
    fun `Test AbstractBodyModel compareTo function, compare Less than`() {
        // Given
        val paragraph1 = Paragraph(1, "")
        val paragraph2 = Paragraph(2, "")

        // Then
        Assert.assertTrue(paragraph1 < paragraph2)
    }

    @Test
    fun `Test AbstractBodyModel compareTo function, compare Greater than`() {
        // Given
        val paragraph1 = Paragraph(1, "")
        val paragraph2 = Paragraph(2, "")

        // Then
        Assert.assertFalse(paragraph2 < paragraph1)
    }

    @Test
    fun `Test AbstractBodyModel compareTo function, compare Equals than`() {
        // Given
        val paragraph1 = Paragraph(1, "")
        val paragraph2 = Paragraph(1, "")

        // Then
        Assert.assertTrue(paragraph1 == paragraph2)
    }
}