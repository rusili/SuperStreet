package com.rusili.superstreet.jsoup.list

import com.rusili.superstreet.common.models.Magazine
import com.rusili.superstreet.jsoup.parsing.CommonParser
import com.rusili.superstreet.jsoup.parsing.PreviewListParser
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotBe
import org.jsoup.Jsoup
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.File

class PreviewListTest {
    private val inputMain =
        File("/Users/rusi.li/Documents/Android/SuperStreet/app/src/test/resources/html/SuperStreetHomepage.html")
    private val doc = Jsoup.parse(inputMain, "UTF-8", "")

    private val parser = PreviewListParser(CommonParser())
    private lateinit var previewList: List<ArticlePreviewModel>

    @Before
    fun setup() {
        previewList = parser.parseToList(doc)
    }

    @Test
    fun `Test Top Story Header Parsing`() {
        // Then
        previewList.size shouldEqual 13

        previewList.forEach {
            it.header.isValid()
        }
    }

    @Test
    fun `Test Footer parsing`() {
        // Given
        previewList.forEach {
            it.footer.isValid()
        }
    }

    @Test
    fun `Test Flag parsing`() {
        // Given
        previewList.forEach {
            it.flag.isValid()
        }
    }
}
