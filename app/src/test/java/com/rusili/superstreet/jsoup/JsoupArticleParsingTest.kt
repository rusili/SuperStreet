package com.rusili.superstreet.jsoup

import com.rusili.superstreet.article.domain.ArticleFullModel
import com.rusili.superstreet.jsoup.parsing.ArticleParser
import com.rusili.superstreet.jsoup.parsing.CommonParser
import org.amshove.kluent.shouldEqual
import org.jsoup.Jsoup
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat

class JsoupArticleParsingTest {
    private val doc = Jsoup.connect("http://www.superstreetonline.com/features/18052005-boxster-and-2007-cayman-s-porsche-pandemic/").get()

    private val commonParser = CommonParser()
    private val parser = ArticleParser(commonParser)
    private lateinit var article: ArticleFullModel

    @BeforeEach
    fun setup() {
        article = parser.parseToArticle(doc)
    }

    @Test
    fun `Test article title parsing`() {
        // Given
        val title = article.header.title.value

        // Then
        title shouldEqual "2005 Boxster & 2007 Cayman S - Porsche Pandemic"
    }

    @Test
    fun `Test article desc parsing`() {
        // Given
        val desc = article.header.desc

        // Then
        desc shouldEqual "Osaka's answer to two of Porsche's most disregarded cars"
    }

    @Test
    fun `Test article body parsing`() {

    }

    @Test
    fun `Test article author parsing`() {
        // Given
        val author = article.footer.author.value

        // Then
        author shouldEqual "David Ishikawa"
    }

    @Test
    fun `Test article timestamp parsing`() {
        // Given
        val timestamp = article.footer.date
        val dateFormat = SimpleDateFormat("MMM dd, yyyy")

        // Then
        dateFormat.format(timestamp) shouldEqual "May 18, 2018"
    }

    @Test
    fun `Test article magazine parsing`() {
        // Given
        val value = article.flag.magazine.value
        val href = article.flag.magazine.path

        // Then
        value shouldEqual "Super Street"
        href shouldEqual "/super-street-magazine/"
    }

    @Test
    fun `Test article type parsing`() {
        // Given
        val value = article.flag.type.value
        val href = article.flag.type.path

        // Then
        value shouldEqual "Feature"
        href shouldEqual "/features/"
    }

    @Test
    fun `Test article images parsing`() {
        // Given
        val title = article.header.headerImage.title
        val imgSrc = article.header.headerImage.getDefaultSizeUrl()

        // Then
        title shouldEqual "2005 Porsche Boxster 2007 Porsche Cayman S"
        imgSrc shouldEqual "http://image.superstreetonline.com/f/254875289+w600+h400+q80+re0+cr1+ar0/2005-porsche-boxster-2007-porsche-cayman-s.jpg"
    }
}
