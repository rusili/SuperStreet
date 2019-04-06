package com.rusili.superstreet.jsoup.parsing

import com.rusili.superstreet.article.domain.ArticleFullModel
import com.rusili.superstreet.common.models.Body
import com.rusili.superstreet.common.models.Header
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
        val rawFlag = doc.getElementsByClass(COMMON.FLAG.value).first()
        val rawInfo = doc.getElementsByClass(COMMON.INFO.value).first()
        val rawHeaderImage = doc.getElementsByClass(ARTICLE.HEADER_IMAGE.value).second()

        val flag = commonParser.parseFlagElement(rawFlag)
        val header = parseArticleHeaderElement(rawInfo, rawHeaderImage)
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

        val rawArticleParagraphs = rawArticleBody.first().getElementsByClass(ARTICLE_BODY.ARTICLE_PARAGRAPH.value)
        for (element in rawArticleParagraphs) {
            val id = element.attr(ARTICLE_BODY.ID.value).replace(ARTICLE_BODY.ARTICLE_PARAGRAPH.value + "-", "")
            val text = element.getElementsByClass(ARTICLE_BODY.ARTICLE_TEXT.value).first().text()
            articleParagraphList.add(Paragraph(id.toInt(), text))
        }

        val rawArticleImages = rawArticleBody.first().getElementsByClass(ARTICLE_BODY.ARTICLE_IMAGE.value)
        for (element in rawArticleImages) {
            val id = element.attr(ARTICLE_BODY.ID.value).replace(ARTICLE_BODY.ARTICLE_IMAGE.value + "-", "")
            val img = element.getElementsByClass(ARTICLE_BODY.IMG_LINK.value).first()
            val imgSrc = img.getElementsByTag(COMMON.IMG.value).attr(ARTICLE_BODY.DATA_IMG_SRC.value)
            articleImageGalleryList.add(Image(id.toInt(), imgSrc))
        }

        val rawArticleImageGroups = rawArticleBody.first().getElementsByClass(ARTICLE_BODY.ARTICLE_IMAGE_GROUP.value)
        for (element in rawArticleImageGroups) {
            val id = element.attr(ARTICLE_BODY.ID.value).replace(ARTICLE_BODY.ARTICLE_IMAGE_GROUP.value + "-", "")
            val imgGroup = element.getElementsByTag(ARTICLE_BODY.UL.value).first()

            val imgSet = mutableListOf<Image>()
            val rawImgs = imgGroup.getElementsByClass(ARTICLE_BODY.IMG_WRAP.value)
            for (imgElement in rawImgs) {
                val img = imgElement.getElementsByTag(COMMON.DIV.value).first().getElementsByTag(COMMON.A.value)
                val imgSrc = img.first().getElementsByTag(COMMON.IMG.value).first().attr(ARTICLE_BODY.DATA_IMG_SRC.value)
                imgSet.add(Image(-1, imgSrc))
            }
            articleImageGroupList.add(ImageGroup(id.toInt(), imgSet.toList()))
        }

        return Body(articleParagraphList.toList(), articleImageGalleryList.toList(), articleImageGroupList.toList())
    }

    private fun parseArticleImage(image: Element): HeaderImage {
        val itemprop = image.getElementsByClass(ARTICLE_HEADER.IMG_WRAP.value).first().select(COMMON.A.value).first()
        val imgTitle = itemprop.attr(COMMON.TITLE.value)
        val imgSrc = itemprop.select(COMMON.IMG.value).attr(COMMON.SRC.value)

        return HeaderImage(imgTitle, imgSrc)
    }
}
