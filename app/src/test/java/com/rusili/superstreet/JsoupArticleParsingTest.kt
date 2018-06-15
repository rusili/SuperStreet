package com.rusili.superstreet

import com.rusili.superstreet.data.article.ArticleMapper
import com.rusili.superstreet.data.util.FlagMapper
import com.rusili.superstreet.domain.article.ArticleFullModel
import org.jsoup.Jsoup
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import java.io.File
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZoneId.systemDefault
import java.util.*


class JsoupArticleParsingTest {
    private val inputMain = File("/Users/rusi.li/Documents/SuperStreet/app/src/test/resources/json/2007Boxter&CaymanArticle.html")
    private val doc = Jsoup.parse(inputMain, "UTF-8", "")

    private val flagMapper = FlagMapper()
    private val parser = ArticleMapper(flagMapper)
    private lateinit var article: ArticleFullModel

    @Before
    fun setup() {
        article = parser.parseToArticle(doc)
    }

    @Test
    fun `Test article title parsing`() {
        // Given
        val title = article.header.title.value

        // Then
        Assert.assertEquals(title, "2005 Boxster & 2007 Cayman S - Porsche Pandemic")
    }

    @Test
    fun `Test article desc parsing`() {
        // Given
        val desc = article.header.desc

        // Then
        Assert.assertEquals(desc, "Osaka's answer to two of Porsche's most disregarded cars")
    }

    @Test
    fun `Test article body parsing`() {

    }

    @Test
    fun `Test article author parsing`() {
        // Given
        val author = article.footer.author.value

        // Then
        Assert.assertEquals(author, "David Ishikawa")
    }

    @Test
    fun `Test article timestamp parsing`() {
        // Given
        val timestamp = article.footer.date
        val dateFormat = SimpleDateFormat("MMM dd, yyyy")

        // Then
        Assert.assertEquals(dateFormat.format(timestamp), "May 18, 2018")
    }

    @Test
    fun `Test article magazine parsing`() {
        // Given
        val value = article.flag.magazine.value
        val href = article.flag.magazine.href

        // Then
        Assert.assertEquals(value, "Super Street")
        Assert.assertEquals(href, "/super-street-magazine/")
    }

    @Test
    fun `Test article type parsing`() {
        // Given
        val value = article.flag.type.value
        val href = article.flag.type.href

        // Then
        Assert.assertEquals(value, "Features")
        Assert.assertEquals(href, "/features/")
    }

    @Test
    fun `Test article images parsing`() {
        // Given
        val title = article.header.image.title
        val imgSmall = article.header.image.imgSmall
        val imgFull = article.header.image.imgFull

        // Then
        Assert.assertEquals(title, "2005 Porsche Boxster 2007 Porsche Cayman S")
        Assert.assertEquals(imgSmall, "http://image.superstreetonline.com/f/254875289+w660+h440+q80+re0+cr1+ar0/2005-porsche-boxster-2007-porsche-cayman-s.jpg")
        Assert.assertEquals(imgFull, "http://image.superstreetonline.com/f/254875289+w+h+q80+re0+cr1+ar0/2005-porsche-boxster-2007-porsche-cayman-s.jpg")
    }
}
