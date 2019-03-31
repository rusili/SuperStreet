package com.rusili.superstreet.common

import com.rusili.superstreet.common.models.Body
import com.rusili.superstreet.common.models.body.ArticleViewType
import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.ImageGroup
import com.rusili.superstreet.common.models.body.Paragraph
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

class BodyTest {

    @Test
    fun `Given valid sections, When combineSections() is called, Then return all lists combined and in order`() {
        // Given
        val paragraph1 = Paragraph(1, "")
        val paragraph2 = Paragraph(3, "")
        val paragraph3 = Paragraph(6, "")
        val paragraphList = listOf(paragraph1, paragraph2, paragraph3)

        val image1 = Image(2, "")
        val image2 = Image(4, "")
        val imageList = listOf(image1, image2)

        val imageGroup1 = ImageGroup(5, imageList)
        val imageGroup2 = ImageGroup(7, imageList)
        val imageGroupList = listOf(imageGroup1, imageGroup2)

        // When
        val result = Body(paragraphList, imageList, imageGroupList).getCombinedSections()

        // Then
        result.size shouldEqual 7
        result[0].getViewType() shouldEqual ArticleViewType.Paragraph.viewType
        result[1].getViewType() shouldEqual ArticleViewType.Image.viewType
        result[2].getViewType() shouldEqual ArticleViewType.Paragraph.viewType
        result[3].getViewType() shouldEqual ArticleViewType.Image.viewType
        result[4].getViewType() shouldEqual ArticleViewType.ImageGroup.viewType
        result[5].getViewType() shouldEqual ArticleViewType.Paragraph.viewType
        result[6].getViewType() shouldEqual ArticleViewType.ImageGroup.viewType
    }
}
