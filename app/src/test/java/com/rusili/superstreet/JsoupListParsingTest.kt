package com.rusili.superstreet

import com.rusili.superstreet.data.SuperStreetParser
import com.rusili.superstreet.domain.list.model.ArticlePreview
import org.jsoup.Jsoup
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import java.io.File

class JsoupListParsingTest {
    private val inputMain = File("/Users/rusi.li/Documents/SuperStreet/app/src/test/resources/json/SuperStreetHomepage.html")
    private val doc = Jsoup.parse(inputMain, "UTF-8", "")

    private val parser = SuperStreetParser()
    private lateinit var previewList: List<ArticlePreview>

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

    }

    @Test
    fun `Test preview link parsing`(){

    }

    @Test
    fun `Test preview desc parsing`(){

    }

    @Test
    fun `Test preview author parsing`(){
        val author = previewList.get(0).footer.author
        Assert.assertEquals(author, "David Ishikawa")

    }

    @Test
    fun `Test preview timestamp parsing`(){
        val timestamp = previewList.get(0).footer.timestamp
        Assert.assertEquals(timestamp, "May 18, 2018")

    }

    @Test
    fun `Test preview magazine parsing`(){

    }

    @Test
    fun `Test preview type parsing`(){

    }
}
