package com.rusili.superstreet.jsoup.list

import com.rusili.superstreet.jsoup.parsing.CommonParser
import com.rusili.superstreet.jsoup.parsing.PreviewListParser
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import org.amshove.kluent.shouldEqual
import org.jsoup.Jsoup
import org.junit.Before
import org.junit.Test
import java.io.File

class TopStoryTest {
    private val inputMain = File("/Users/rusi.li/Documents/Android/SuperStreet/app/src/test/resources/html/SuperStreetHomepage.html")
    private val doc = Jsoup.parse(inputMain, "UTF-8", "")

    private val parser = PreviewListParser(CommonParser())
    private lateinit var previewList: List<ArticlePreviewModel>

    @Before
    fun setup() {
        previewList = parser.parseToList(doc)
    }

    @Test
    fun `Test Top Story Header Parsing`() {
        // Given
        val title = previewList.first().header.title.value
        val link = previewList.first().header.title.href
        val desc = previewList.first().header.desc

        // Then
        title shouldEqual "1993 Mazda RX-7 - Sacrilicious"
        link shouldEqual "/features/1905-1993-mazda-rx-7-sacrilicious"
        desc shouldEqual "Death, taxes, and the agony of rotary purists: three things that can be counted on from now until the end of time—especially if you decide to build a car like Khiem Pham's RX-7."
    }

    @Test
    fun `Test HeaderImage parsing`() {
        // Given
        val imageLink = previewList.first().header.headerImage.getDefaultSizeUrl()
        val imageTitle = previewList.first().header.headerImage.title

        // Then
        imageLink shouldEqual "http://assets.superstreetonline.com/f/179397085.jpg?width=540&height=360"
        imageTitle shouldEqual "1993 Mazda RX-7 - Sacrilicious"
    }

    @Test
    fun `Test Footer parsing`() {
        // Given
        val author = previewList.first().footer.author.value
        val timestamp = previewList.first().footer.date

        // Then
        author shouldEqual "Benjamin Hunting"
        timestamp.toLocaleString() shouldEqual "May 1, 2019 12:00:00 AM"
    }

    @Test
    fun `Test Flag parsing`() {
        // Given
        val magazineValue = previewList.first().flag.magazine.value
        val magazinePath = previewList.first().flag.magazine.path
        val typeValue = previewList.first().flag.type.value
        val typePath = previewList.first().flag.type.path

        // Then
        magazineValue shouldEqual "Super Street"
        magazinePath shouldEqual "/super-street-magazine/"
        typeValue shouldEqual "Feature"
        typePath shouldEqual "/features/"
    }
}
