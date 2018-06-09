package com.rusili.superstreet.data

import com.rusili.superstreet.data.util.*
import com.rusili.superstreet.domain.article.ArticleFullModel
import com.rusili.superstreet.domain.list.ArticlePreviewModel
import com.rusili.superstreet.domain.models.Flag
import com.rusili.superstreet.domain.models.Footer
import com.rusili.superstreet.domain.models.Header
import com.rusili.superstreet.domain.models.body.ImageGallery
import com.rusili.superstreet.domain.models.body.ImageGroup
import com.rusili.superstreet.domain.models.body.Paragraph
import com.rusili.superstreet.domain.models.flag.Type
import com.rusili.superstreet.domain.models.footer.Author
import com.rusili.superstreet.domain.models.header.Image
import com.rusili.superstreet.domain.models.header.Title
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Parses an Html document to Super Street models.
 */
class SuperStreetMapper @Inject constructor(private val flagMapper: FlagMapper) {

    fun parseToList(doc: Document): List<ArticlePreviewModel> {
        val previewsList = ArrayList<ArticlePreviewModel>()

        val mainColumn = doc.getElementsByClass(LIST.MAIN_COLUMN.value).first()
        val topStoryElement = mainColumn.getElementsByClass(LIST.TOP_STORY.value).first()
        val stories = mainColumn.getElementsByClass(LIST.STORIES_CONTAINER.value).first()

        val topStoryFlag = parseFlagElement(topStoryElement)
        val topStoryHeader = parseFeatureHeaderElement(topStoryElement)
        val topStoryFooter = parseFooterElement(topStoryElement)
        val topStoryArticlePreviewModel = ArticlePreviewModel(topStoryFlag, topStoryHeader, topStoryFooter)

        previewsList.add(topStoryArticlePreviewModel)

        for (story in stories.children()) {
            if (story.hasClass(LIST.PART_ITEM.value) || story.hasClass(LIST.PART_HERO.value)) {
                val flag = parseFlagElement(story)
                val header = parseFeatureHeaderElement(story)
                val footer = parseFooterElement(story)

                val articlePreview = ArticlePreviewModel(flag, header, footer)
                previewsList.add(articlePreview)
            }
        }
        return previewsList
    }

    fun parseToArticle(doc: Document): ArticleFullModel {
        val flags = doc.getElementsByClass(COMMON.FLAG.value)
        val article = doc.getElementsByClass(COMMON.INFO.value).first()
        val image = doc.getElementsByClass(ARTICLE.HEADER_IMAGE.value)[1]

        val flag = parseFlagElement(flags.first())
        val header = parseArticleHeaderElement(article, image)
        val body = parseArticleBody(doc)
        val footer = parseFooterElement(article)

        val articleModel = ArticleFullModel(flag, header, footer)

        return articleModel
    }

    private fun parseArticleBody(doc: Document): Any {
        val articleParagraphList = mutableListOf<Paragraph>()
        val articleImageGalleryList = mutableListOf<ImageGallery>()
        val articleImageGroupList = mutableListOf<ImageGroup>()

        val articleContent = doc.getElementsByClass("mod-article-content")
        val articleBody = articleContent.first().getElementsByClass("article-page")

        val articleParagraphs = articleBody.first().getElementsByClass("article-paragraph")
        for (element in articleParagraphs) {
            val id = element.attr("id").replace("article-paragraph-", "")
            val text = element.getElementsByClass("article-text").first().text()
            articleParagraphList.add(Paragraph(id.toInt(), text))
        }

        val articleImages = articleBody.first().getElementsByClass("article-image")
        for (element in articleImages) {
            val id = element.attr("id").replace("article-image-", "")
            val image = element.getElementsByClass("img-link").first()
            val imageFull = image.attr("href")
            val imageSmall = image.getElementsByTag("img").attr("data-img-src")
            articleImageGalleryList.add(ImageGallery(id.toInt(), imageSmall, imageFull))
        }

        val articleImageGroups = articleBody.first().getElementsByClass("article-image-group")
        for (element in articleImageGroups) {
            val id = element.attr("id").replace("article-image-group-", "")
            val imageGroup = element.getElementsByTag("ul").first()

            val imageSet = mutableSetOf<ImageGallery>()
            val images = imageGroup.getElementsByClass("img-wrap")
            for (imageElement in images) {
                val image = imageElement.getElementsByTag("div").first().getElementsByTag("a")
                val imageFull = image.attr("href")
                val imageThumb = image.first().getElementsByTag("img").first().attr("data-img-src")
                imageSet.add(ImageGallery(-1, imageThumb, imageFull))
            }
            articleImageGroupList.add(ImageGroup(id.toInt(), imageSet))
        }

        return articleBody
    }

    private fun parseFlagElement(element: Element): Flag {
        // Magazine
        val flagMag = element.select(COMMON.A.value).first()
        val flagMagValue = flagMag.attr(FLAG.TITLE.value).toString()
        val magazine = flagMapper.getMagazine(flagMagValue)

        // Type
        val type = buildType(element)

        return Flag(magazine, type)
    }

    private fun buildType(element: Element): Type {
        val flagType = element.select(COMMON.A.value)[1]

        var flagTypeValue = flagType.select("span.label").text()    // For Article parsing
        if (flagTypeValue.isBlank()) {
            flagTypeValue = flagType.textNodes().first().text()      // For Preview parsing
        }

        return flagMapper.getType(flagTypeValue)
    }

    private fun parseFeatureHeaderElement(element: Element): Header {
        // TitlePreview
        val infoNode = element.select("div.info").first()
        val titleNode = infoNode.select(COMMON.A.value).first()

        val titleValue = titleNode.attr(HEADER.TITLE.value)
        val titleHrefEndpoint = titleNode.attr(COMMON.HREF.value)
        val title = Title(titleValue, titleHrefEndpoint)

        // Desc
        val desc = element.select(HEADER.DESC.value).text()

        // Image:
        var imageTitle: String
        var imageHref: String

        val nonFeatureImageNode = infoNode.select(HEADER.IMG.value)        // For non-feature stories:
        if (nonFeatureImageNode.isNotEmpty()) {
            imageTitle = nonFeatureImageNode.first().attr(HEADER.DATA_ALT.value)
            imageHref = nonFeatureImageNode.first().attr(HEADER.DATA_SRC.value)
        } else {
            val imageNode = element.children()[1].select(COMMON.A.value)            // For feature stories:
            val featureImageNode = imageNode.select("img")

            // Top Story:
            imageTitle = featureImageNode.attr("alt") // "title"
            imageHref = featureImageNode.attr("src") // "data-src"

            // Feature Stories:
            if (imageTitle.isBlank() && imageHref.isBlank()) {
                imageTitle = featureImageNode.attr("title")
                imageHref = featureImageNode.attr("data-src")
            }
        }
        val image = Image(imageTitle, imageHref)

        return Header(title, image, desc)
    }

    private fun parseArticleHeaderElement(element: Element, imageEle: Element): Header {
        val header = element.getElementsByClass(COMMON.INFO.value).first()

        // TitlePreview
        val titleValue = header.select("h1.title").text()
        val title = Title(titleValue, "")

        // Desc
        val desc = header.select("h2.desc").text()

        // Image
        val image = buildArticleImage(imageEle)

        return Header(title, image, desc)
    }

    private fun parseFooterElement(element: Element): Footer {
        // Author
        var href = ""
        var value = element.select(FOOTER.AUTHOR_CONTRIBUTING_1.value).text()
        if (value.isBlank()) {
            value = element.select(FOOTER.AUTHOR_CONTIBUTING_2.value).text()
        }
        if (value.isBlank()) {
            val ele = element.select(FOOTER.AUTHOR_STAFF_DIV.value)
                    .select(FOOTER.AUTHOR_STAFF_ATTR.value)
            value = ele.text()
            href = ele.attr(COMMON.HREF.value).toString()
        }

        // Timestamp
        val timestamp = element.select(FOOTER.TIMESTAMP.value).text()
        val format = SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
        val date = format.parse(timestamp)

        return Footer(Author(value, href), date)
    }

    private fun buildPreviewImage(header: Element): Image {
        var imgSrc = ""
        var imgTitle = ""

        val img = header.select(HEADER.IMG.value)
        if (img.isNotEmpty()) {
            imgSrc = img.first().attr(HEADER.DATA_ALT.value)
            imgTitle = img.first().attr(HEADER.DATA_SRC.value)
        }

        return Image(imgSrc, imgTitle)
    }

    private fun buildArticleImage(image: Element): Image {
        val itemprop = image.getElementsByClass("img-wrap").first().select("a").first()
        val imgTitle = itemprop.attr("title")
        val imgOriginal = itemprop.attr("href")
        val imgSmall = itemprop.select("img").attr("src")

        return Image(imgTitle, imgSmall)
    }
}