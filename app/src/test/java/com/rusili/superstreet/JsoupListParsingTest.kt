package com.rusili.superstreet

import com.rusili.superstreet.data.SuperStreetMapper
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import org.jsoup.Jsoup
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import java.io.File

class JsoupListParsingTest {
    private val inputMain = File("/Users/rusi.li/Documents/SuperStreet/app/src/test/resources/json/SuperStreetHomepage.html")
    private val doc = Jsoup.parse(inputMain, "UTF-8", "")

    private val parser = SuperStreetMapper()
    private lateinit var previewList: List<ArticlePreviewModel>

    @Before
    fun setup(){
        previewList = parser.parseToList(doc)
    }

    @Test
    fun `Test preview top story parsing`(){

    }

    @Test
    fun `Test random preview parsing`(){

    }

    @Test
    fun `Test preview titles parsing`(){
        // Given
        val author = previewList.get(0).header.title.value

        // Then
        Assert.assertEquals(author, "2005 Boxster & 2007 Cayman S - Porsche Pandemic")
    }

    @Test
    fun `Test preview link parsing`(){
        // Given
        val author = previewList.get(0).header.title.href

        // Then
        Assert.assertEquals(author, "/features/18052005-boxster-and-2007-cayman-s-porsche-pandemic/")
    }

    @Test
    fun `Test preview desc parsing`(){
        // Given
        val author = previewList.get(0).header.desc

        // Then
        Assert.assertEquals(author, "These Porsches may not have a whole lot of mods or be built to be best-of-show winners, but they do have sweet interiors, and swagger you'll only be able to find in Japan.")
    }

    @Test
    fun `Test preview author parsing`(){
        // Given
        val author = previewList.get(0).footer.author

        // Then
        Assert.assertEquals(author, "David Ishikawa")
    }

    @Test
    fun `Test preview timestamp parsing`(){
        // Given
        val timestamp = previewList.get(0).footer.timestamp

        // Then
        Assert.assertEquals(timestamp, "May 18, 2018")
    }

    @Test
    fun `Test preview magazine parsing`(){
        // Given
        val text = previewList.get(0).flag.magazine.value
        val href = previewList.get(0).flag.magazine.href

        // Then
        Assert.assertEquals(text, "SuperStreetOnline Magazine")
        Assert.assertEquals(href, "/super-street-magazine/")
    }

    @Test
    fun `Test preview type parsing`(){
        // Given
        val text = previewList.get(0).flag.type.value
        val href = previewList.get(0).flag.type.href

        // Then
        Assert.assertEquals(text, "features")
        Assert.assertEquals(href, "/features/")
    }
}
