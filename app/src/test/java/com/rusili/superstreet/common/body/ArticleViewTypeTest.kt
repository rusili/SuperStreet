package com.rusili.superstreet.common.body

import com.rusili.superstreet.common.models.body.ArticleViewType
import org.amshove.kluent.shouldEqual
import org.junit.Test

class ArticleViewTypeTest {

    @Test
    fun`Given set viewtypes, When getViewtype() is called, Then return correct int`(){
        // Given
        val header = 0
        val paragraph = 1
        val image = 2
        val imageGroup = 3
        val gallery = 4

        // When
        val result1 = ArticleViewType.Header.viewType
        val result2 = ArticleViewType.Paragraph.viewType
        val result3 = ArticleViewType.Image.viewType
        val result4 = ArticleViewType.ImageGroup.viewType
        val result5 = ArticleViewType.Gallery.viewType

        // Then
        result1 shouldEqual header
        result2 shouldEqual paragraph
        result3 shouldEqual image
        result4 shouldEqual imageGroup
        result5 shouldEqual gallery
    }
}
