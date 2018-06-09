package com.rusili.superstreet.data.article

import com.rusili.superstreet.data.util.*
import com.rusili.superstreet.domain.article.ArticleFullModel
import com.rusili.superstreet.domain.models.ArticleHeader
import com.rusili.superstreet.domain.models.body.ImageGallery
import com.rusili.superstreet.domain.models.body.ImageGroup
import com.rusili.superstreet.domain.models.body.Paragraph
import com.rusili.superstreet.domain.models.header.HeaderImageArticle
import com.rusili.superstreet.domain.models.header.Title
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import javax.inject.Inject

/**
 * Parses an Html document to Super Street models.
 */
class ArticleMapper @Inject constructor(flagMapper: FlagMapper) : BaseMapper(flagMapper) {

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

    private fun parseArticleHeaderElement(element: Element, imageEle: Element): ArticleHeader {
        val header = element.getElementsByClass(COMMON.INFO.value).first()

        // TitlePreview
        val titleValue = header.select("h1.title").text()
        val title = Title(titleValue, "")

        // Desc
        val desc = header.select("h2.desc").text()

        // Image
        val image = buildArticleImage(imageEle)

        return ArticleHeader(title, image, desc)
    }

    private fun buildArticleImage(image: Element): HeaderImageArticle {
        val itemprop = image.getElementsByClass("img-wrap").first().select("a").first()
        val imgTitle = itemprop.attr("title")
        val imgFull = itemprop.attr("href")
        val imgSmall = itemprop.select("img").attr("src")

        return HeaderImageArticle(imgTitle, imgSmall, imgFull)
    }
}