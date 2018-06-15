package com.rusili.superstreet.data.article

import com.rusili.superstreet.data.util.*
import com.rusili.superstreet.domain.article.ArticleFullModel
import com.rusili.superstreet.domain.models.ArticleHeader
import com.rusili.superstreet.domain.models.Body
import com.rusili.superstreet.domain.models.body.ImageGallery
import com.rusili.superstreet.domain.models.body.ImageGroup
import com.rusili.superstreet.domain.models.body.Paragraph
import com.rusili.superstreet.domain.models.header.HeaderImageArticle
import com.rusili.superstreet.domain.models.header.Title
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import javax.inject.Inject

/**
 * Parses an Html file to Article-related models.
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

        val articleModel = ArticleFullModel(flag, header, body, footer)

        return articleModel
    }

    private fun parseArticleHeaderElement(element: Element,
                                          imageEle: Element): ArticleHeader {
        val header = element.getElementsByClass(COMMON.INFO.value).first()

        // TitlePreview
        val titleValue = header.select(ARTICLE_HEADER.TITLE.value).text()
        val title = Title(titleValue, "")

        // Desc
        val desc = header.select(ARTICLE_HEADER.DESC.value).text()

        // Image
        val image = buildArticleImage(imageEle)

        return ArticleHeader(title, image, desc)
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
            val image = element.getElementsByClass(ARTICLE_BODY.IMG_LINK.value).first()
            val imageFull = image.attr(COMMON.HREF.value)
            val imageSmall = image.getElementsByTag(COMMON.IMG.value).attr(ARTICLE_BODY.DATA_IMG_SRC.value)
            articleImageGalleryList.add(ImageGallery(id.toInt(), imageSmall, imageFull))
        }

        val articleImageGroups = articleBody.first().getElementsByClass(ARTICLE_BODY.ARTICLE_IMAGE_GROUP.value)
        for (element in articleImageGroups) {
            val id = element.attr(ARTICLE_BODY.ID.value).replace(ARTICLE_BODY.ARTICLE_IMAGE_GROUP.value + "-", "")
            val imageGroup = element.getElementsByTag(ARTICLE_BODY.UL.value).first()

            val imageSet = mutableListOf<ImageGallery>()
            val images = imageGroup.getElementsByClass(ARTICLE_BODY.IMG_WRAP.value)
            for (imageElement in images) {
                val image = imageElement.getElementsByTag(COMMON.DIV.value).first().getElementsByTag(COMMON.A.value)
                val imageFull = image.attr(COMMON.HREF.value)
                val imageThumb = image.first().getElementsByTag(COMMON.IMG.value).first().attr(ARTICLE_BODY.DATA_IMG_SRC.value)
                imageSet.add(ImageGallery(-1, imageThumb, imageFull))
            }
            articleImageGroupList.add(ImageGroup(id.toInt(), imageSet))
        }

        return Body(articleParagraphList, articleImageGalleryList, articleImageGroupList)
    }

    private fun buildArticleImage(image: Element): HeaderImageArticle {
        val itemprop = image.getElementsByClass(ARTICLE_HEADER.IMG_WRAP.value).first().select(COMMON.A.value).first()
        val imgTitle = itemprop.attr(COMMON.TITLE.value)
        val imgFull = itemprop.attr(COMMON.HREF.value)
        val imgSmall = itemprop.select(COMMON.IMG.value).attr(COMMON.SRC.value)

        return HeaderImageArticle(imgTitle, imgSmall, imgFull)
    }
}