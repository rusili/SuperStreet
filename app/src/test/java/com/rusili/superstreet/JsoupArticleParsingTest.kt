package com.rusili.superstreet

import com.rusili.superstreet.data.FlagMapper
import com.rusili.superstreet.data.SuperStreetMapper
import com.rusili.superstreet.domain.article.ArticleFullModel
import org.jsoup.Jsoup
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import java.io.File

class JsoupArticleParsingTest {
    private val inputMain = File("/Users/rusi.li/Documents/SuperStreet/app/src/test/resources/json/2007Boxter&CaymanArticle.html")
    private val doc = Jsoup.parse(inputMain, "UTF-8", "")

    private val flagMapper = FlagMapper()
    private val parser = SuperStreetMapper(flagMapper)
    private lateinit var article: ArticleFullModel

    @Before
    fun setup(){
//        article = parser.parseToArticle(doc)
    }


    @Test
    fun `Test article title parsing`(){
        // Given
        val title = article.header.title.value

        // Then
        Assert.assertEquals(title, "2005 Boxster & 2007 Cayman S - Porsche Pandemic")
    }

    @Test
    fun `Test article body parsing`(){

    }

    @Test
    fun `Test article author parsing`(){

    }

    @Test
    fun `Test article timestamp parsing`(){

    }

    @Test
    fun `Test article magazine parsing`(){

    }

    @Test
    fun `Test article type parsing`(){

    }

    @Test
    fun `Test article images parsing`(){

    }

    @Test
    fun `Test complete article parsing`(){

    }
}
