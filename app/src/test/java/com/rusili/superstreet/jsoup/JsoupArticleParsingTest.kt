package com.rusili.superstreet.jsoup

import com.rusili.superstreet.jsoup.parsing.ArticleParser
import com.rusili.superstreet.article.domain.ArticleFullModel
import com.rusili.superstreet.jsoup.parsing.CommonParser
import org.jsoup.Jsoup
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat

class JsoupArticleParsingTest {
    private val doc = Jsoup.connect("http://www.superstreetonline.com/features/18052005-boxster-and-2007-cayman-s-porsche-pandemic/").get()

    private val commonParser = CommonParser()
    private val parser = ArticleParser(commonParser)
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
        val href = article.flag.magazine.path

        // Then
        Assert.assertEquals(value, "Super Street")
        Assert.assertEquals(href, "/super-street-magazine/")
    }

    @Test
    fun `Test article type parsing`() {
        // Given
        val value = article.flag.type.value
        val href = article.flag.type.path

        // Then
        Assert.assertEquals(value, "Feature")
        Assert.assertEquals(href, "/features/")
    }

    @Test
    fun `Test article images parsing`() {
        // Given
        val title = article.header.headerImage.title
        val imgSrc = article.header.headerImage.resizeToDefaultSize()

        // Then
        Assert.assertEquals(title, "2005 Porsche Boxster 2007 Porsche Cayman S")
        Assert.assertEquals(imgSrc, "http://image.superstreetonline.com/f/254875289+w600+h400+q80+re0+cr1+ar0/2005-porsche-boxster-2007-porsche-cayman-s.jpg")
    }
}
