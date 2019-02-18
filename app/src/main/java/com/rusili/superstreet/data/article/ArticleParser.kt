package com.rusili.superstreet.data.article

import com.rusili.superstreet.data.util.*
import com.rusili.superstreet.domain.article.ArticleFullModel
import com.rusili.superstreet.domain.models.Body
import com.rusili.superstreet.domain.models.Header
import com.rusili.superstreet.domain.models.body.ImageGallery
import com.rusili.superstreet.domain.models.body.ImageGroup
import com.rusili.superstreet.domain.models.body.Paragraph
import com.rusili.superstreet.domain.models.header.HeaderImage
import com.rusili.superstreet.domain.models.header.Title
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import javax.inject.Inject

/**
 * Parses an Html file to Article-related models.
 */
class ArticleParser @Inject constructor(private val commonParser: CommonParser) {

    fun parseToArticle(doc: Document): ArticleFullModel {
        val flags = doc.getElementsByClass(COMMON.FLAG.value)
        val article = doc.getElementsByClass(COMMON.INFO.value).first()
        val image = doc.getElementsByClass(ARTICLE.HEADER_IMAGE.value)[1]

        val flag = commonParser.parseFlagElement(flags.first())
        val header = parseArticleHeaderElement(article, image)
        val body = parseArticleBody(doc)
        val footer = commonParser.parseFooterElement(article)

        val articleModel = ArticleFullModel(flag, header, body, footer)

        return articleModel
    }

    private fun parseArticleHeaderElement(element: Element,
                                          imageEle: Element): Header {
        val header = element.getElementsByClass(COMMON.INFO.value).first()

        // TitlePreview
        val titleValue = header.select(ARTICLE_HEADER.TITLE.value).text()
        val title = Title(titleValue, "")

        // Desc
        val desc = header.select(ARTICLE_HEADER.DESC.value).text()

        // HeaderImage
        val image = buildArticleImage(imageEle)

        return Header(title, image, desc)
    }

    private fun parseArticleBody(doc: Document): Body {
        val articleParagraphList = mutableListOf<Paragraph>()
        val articleImageGalleryList = mutableListOf<ImageGallery>()
        val articleImageGroupList = mutableListOf<ImageGroup>()

        val articleContent = doc.getElementsByClass(ARTICLE_BODY.MOD_ARTICLE_CONTENT.value)
        val articleBody = articleContent.first().getElementsByClass(ARTICLE_BODY.ARTICLE_PAGE.value)

        val articleParagraphs = articleBody.first().getElementsByClass(ARTICLE_BODY.ARTICLE_PARAGRAPH.value)
        for (element in articleParagraphs) {
            val id = element.attr(ARTICLE_BODY.ID.value).replace(ARTICLE_BODY.ARTICLE_PARAGRAPH.value + "-", "")
            val text = element.getElementsByClass(ARTICLE_BODY.ARTICLE_TEXT.value).first().text()
            articleParagraphList.add(Paragraph(id.toInt(), text))
        }

        val articleImages = articleBody.first().getElementsByClass(ARTICLE_BODY.ARTICLE_IMAGE.value)
        for (element in articleImages) {
            val id = element.attr(ARTICLE_BODY.ID.value).replace(ARTICLE_BODY.ARTICLE_IMAGE.value + "-", "")
            val img = element.getElementsByClass(ARTICLE_BODY.IMG_LINK.value).first()
            val imgSrc = img.getElementsByTag(COMMON.IMG.value).attr(ARTICLE_BODY.DATA_IMG_SRC.value)
            articleImageGalleryList.add(ImageGallery(id.toInt(), imgSrc))
        }

        val articleImageGroups = articleBody.first().getElementsByClass(ARTICLE_BODY.ARTICLE_IMAGE_GROUP.value)
        for (element in articleImageGroups) {
            val id = element.attr(ARTICLE_BODY.ID.value).replace(ARTICLE_BODY.ARTICLE_IMAGE_GROUP.value + "-", "")
            val imgGroup = element.getElementsByTag(ARTICLE_BODY.UL.value).first()

            val imgSet = mutableListOf<ImageGallery>()
            val imgs = imgGroup.getElementsByClass(ARTICLE_BODY.IMG_WRAP.value)
            for (imgElement in imgs) {
                val img = imgElement.getElementsByTag(COMMON.DIV.value).first().getElementsByTag(COMMON.A.value)
                val imgSrc = img.first().getElementsByTag(COMMON.IMG.value).first().attr(ARTICLE_BODY.DATA_IMG_SRC.value)
                imgSet.add(ImageGallery(-1, imgSrc))
            }
            articleImageGroupList.add(ImageGroup(id.toInt(), imgSet))
        }

        return Body(articleParagraphList, articleImageGalleryList, articleImageGroupList)
    }

    private fun buildArticleImage(image: Element): HeaderImage {
        val itemprop = image.getElementsByClass(ARTICLE_HEADER.IMG_WRAP.value).first().select(COMMON.A.value).first()
        val imgTitle = itemprop.attr(COMMON.TITLE.value)
        val imgSrc = itemprop.select(COMMON.IMG.value).attr(COMMON.SRC.value)

        return HeaderImage(imgTitle, imgSrc)
    }
}
