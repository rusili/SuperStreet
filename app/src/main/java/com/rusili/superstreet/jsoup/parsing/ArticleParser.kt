package com.rusili.superstreet.jsoup.parsing

import com.rusili.superstreet.article.domain.ArticleFullModel
import com.rusili.superstreet.common.extensions.remove
import com.rusili.superstreet.common.models.Body
import com.rusili.superstreet.common.models.Header
import com.rusili.superstreet.common.models.ImageUrl
import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.ImageGroup
import com.rusili.superstreet.common.models.body.Paragraph
import com.rusili.superstreet.common.models.header.HeaderImage
import com.rusili.superstreet.common.models.header.Title
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import javax.inject.Inject

/**
 * Parses an Html file to Article-related models.
 */
class ArticleParser @Inject constructor(private val commonParser: CommonParser) {

    fun parseToArticle(doc: Document): ArticleFullModel {
        val rawInfo = doc.getElementsByClass(COMMON.INFO.value).first()

        val flag = commonParser.parseFlagElement(doc.getElementsByClass(COMMON.FLAG.value).first())
        val header = parseArticleHeaderElement(rawInfo, doc.getElementsByClass(ARTICLE.HEADER_IMAGE.value).second())
        val body = parseArticleBody(doc)
        val footer = commonParser.parseFooterElement(rawInfo)

        return ArticleFullModel(flag, header, body, footer)
    }

    private fun parseArticleHeaderElement(
        rawInfo: Element,
        rawHeaderImage: Element
    ): Header {
        val header = rawInfo.getElementsByClass(COMMON.INFO.value).first()

        val title = Title(header.select(ARTICLE_HEADER.TITLE.value).text(), "")
        val image = parseArticleImage(rawHeaderImage)
        val desc = header.select(ARTICLE_HEADER.DESC.value).text()

        return Header(title, image, desc)
    }

    private fun parseArticleBody(doc: Document): Body {
        val articleParagraphList = mutableListOf<Paragraph>()
        val articleImageGalleryList = mutableListOf<Image>()
        val articleImageGroupList = mutableListOf<ImageGroup>()

        val rawArticleBody = doc.getElementsByClass(ARTICLE_BODY.MOD_ARTICLE_CONTENT.value).first()
            .getElementsByClass(ARTICLE_BODY.ARTICLE_PAGE.value)

        rawArticleBody.first().getElementsByClass(ARTICLE_BODY.ARTICLE_PARAGRAPH.value)
            .forEach {
                val id = it.attr(ARTICLE_BODY.ID.value).remove(ARTICLE_BODY.ARTICLE_PARAGRAPH.value + "-")
                val text = it.getElementsByClass(ARTICLE_BODY.ARTICLE_TEXT.value).first().text()
                articleParagraphList.add(Paragraph(id.toInt(), text))
            }

        rawArticleBody.first().getElementsByClass(ARTICLE_BODY.ARTICLE_IMAGE.value).forEach {
            val id = it.attr(ARTICLE_BODY.ID.value).remove(ARTICLE_BODY.ARTICLE_IMAGE.value + "-")
            val img = it.getElementsByClass(ARTICLE_BODY.IMG_LINK.value).first()
            val imgSrc = img.getElementsByTag(COMMON.IMG.value).attr(ARTICLE_BODY.DATA_IMG_SRC.value).trim()
            articleImageGalleryList.add(Image(id.toInt(), ImageUrl(imgSrc)))
        }

        rawArticleBody.first().getElementsByClass(ARTICLE_BODY.ARTICLE_IMAGE_GROUP.value).forEach {
            val id = it.attr(ARTICLE_BODY.ID.value).remove(ARTICLE_BODY.ARTICLE_IMAGE_GROUP.value + "-")
            val imgGroup = it.getElementsByTag(ARTICLE_BODY.UL.value).first()

            val imgSet = mutableListOf<Image>()
            imgGroup.getElementsByClass(ARTICLE_BODY.IMG_WRAP.value).forEach {
                val img = it.getElementsByTag(COMMON.DIV.value).first().getElementsByTag(COMMON.A.value)
                val imgSrc = img.first().getElementsByTag(COMMON.IMG.value).first().attr(ARTICLE_BODY.DATA_IMG_SRC.value).trim()
                imgSet.add(Image(-1, ImageUrl(imgSrc)))
            }
            articleImageGroupList.add(ImageGroup(id.toInt(), imgSet.toList()))
        }

        return Body(articleParagraphList.toList(), articleImageGalleryList.toList(), articleImageGroupList.toList())
    }

    private fun parseArticleImage(image: Element): HeaderImage {
        val itemprop = image.getElementsByClass(ARTICLE_HEADER.IMG_WRAP.value).first().select(COMMON.A.value).first()
        val imgTitle = itemprop.attr(COMMON.TITLE.value)
        val imgSrc = itemprop.select(COMMON.IMG.value).attr(COMMON.SRC.value).trim()

        return HeaderImage(imgTitle, ImageUrl(imgSrc))
    }
}
