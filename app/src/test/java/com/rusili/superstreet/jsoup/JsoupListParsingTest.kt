package com.rusili.superstreet.jsoup

//package com.rusili.superstreet.data
//
//import com.rusili.superstreet.jsoup.parsing.PreviewListParser
//import com.rusili.superstreet.data.util.FlagMapper
//import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
//import org.jsoup.Jsoup
//import org.junit.Assert
//import org.junit.Before
//import org.junit.Test
//import java.io.File
//
//class JsoupListParsingTest {
//    private val inputMain = File("/Users/rusi.li/Documents/SuperStreet/app/src/test/resources/html/SuperStreetHomepage.html")
//    private val doc = Jsoup.parse(inputMain, "UTF-8", "")
//
//    private val flagMapper = FlagMapper()
//    private val parser = PreviewListParser(flagMapper)
//    private lateinit var previewList: List<ArticlePreviewModel>
//
//    @Before
//    fun setup(){
//        previewList = parser.parseToList(doc)
//    }
//
//    @Test
//    fun `Test preview top story parsing`(){
//
//    }
//
//    @Test
//    fun `Test random preview parsing`(){
//
//    }
//
//    @Test
//    fun `Test preview titles parsing`(){
//        // Given
//        val title = previewList.get(0).header.title.value
//
//        // Then
//        Assert.assertEquals(title, "2005 Boxster & 2007 Cayman S - Porsche Pandemic")
//    }
//
//    @Test
//    fun `Test preview link parsing`(){
//        // Given
//        val link = previewList.get(0).header.title.path
//
//        // Then
//        Assert.assertEquals(link, "/features/18052005-boxster-and-2007-cayman-s-porsche-pandemic/")
//    }
//
//    @Test
//    fun `Test preview desc parsing`(){
//        // Given
//        val desc = previewList.get(0).header.desc
//
//        // Then
//        Assert.assertEquals(desc, "These Porsches may not have a whole lot of mods or be built to be best-of-show winners, but they do have sweet interiors, and swagger you'll only be able to find in Japan.")
//    }
//
//    @Test
//    fun `Test preview image parsing feature story`(){
//        // Given
//        val link = previewList.get(0).header.headerImage.resizeToDefaultSize()
//        val title = previewList.get(0).header.headerImage.title
//
//        // Then
//        Assert.assertEquals(link, "http://image.superstreetonline.com/f/254875289+w600+h400+q80+re0+cr1+ar0/2005-porsche-boxster-2007-porsche-cayman-s.jpg")
//        Assert.assertEquals(title, "2005 Boxster & 2007 Cayman S - Porsche Pandemic")
//    }
//
//    @Test
//    fun `Test preview image parsing non-feature story`(){
//        // Given
//        val link = previewList.get(1).header.headerImage.resizeToDefaultSize()
//        val title = previewList.get(1).header.headerImage.title
//
//        // Then
//        Assert.assertEquals(link, "http://image.superstreetonline.com/f/254929901+w600+h400+q80+re0+cr1+ar0/ss-july-2018-issue-preview-lead.jpg")
//        Assert.assertEquals(title, "Super Street July 2018 Preview – THE JAPAN ISSUE")
//    }
//
//    @Test
//    fun `Test preview author parsing`(){
//        // Given
//        val author = previewList.get(0).footer.author.value
//
//        // Then
//        Assert.assertEquals(author, "David Ishikawa")
//    }
//
//    @Test
//    fun `Test preview timestamp parsing`(){
//        // Given
//        val timestamp = previewList.get(0).footer.date
//
//        // Then
//        Assert.assertEquals(timestamp.toLocaleString(), "May 18, 2018 12:00:00 AM")
//    }
//
//    @Test
//    fun `Test preview magazine parsing`(){
//        // Given
//        val value = previewList.get(0).flag.magazine.value
//        val path = previewList.get(0).flag.magazine.path
//
//        // Then
//        Assert.assertEquals(value, "Super Street")
//        Assert.assertEquals(path, "/super-street-magazine/")
//    }
//
//    @Test
//    fun `Test preview type parsing`(){
//        // Given
//        val value = previewList.get(0).flag.type.value
//        val path = previewList.get(0).flag.type.path
//
//        // Then
//        Assert.assertEquals(value, "Feature")
//        Assert.assertEquals(path, "/features/")
//    }
//}
