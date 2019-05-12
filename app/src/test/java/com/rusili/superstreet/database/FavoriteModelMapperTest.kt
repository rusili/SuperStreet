package com.rusili.superstreet.database

import com.rusili.superstreet.common.models.Flag
import com.rusili.superstreet.common.models.Footer
import com.rusili.superstreet.common.models.Header
import com.rusili.superstreet.common.models.Magazine
import com.rusili.superstreet.common.models.Type
import com.rusili.superstreet.common.models.footer.Author
import com.rusili.superstreet.common.models.header.HeaderImage
import com.rusili.superstreet.common.models.header.Title
import com.rusili.superstreet.database.favorites.FavoriteModelMapper
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import com.rusili.superstreet.previewlist.domain.CardSize
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test
import java.util.Date

class FavoriteModelMapperTest {
    private val testSubject = FavoriteModelMapper()

    private val articlePreviewModel = ArticlePreviewModel(
        Flag(
            Magazine.SuperStreet,
            Type.Feature
        ),
        Header(
            Title(
                "Title",
                "TitleHref"
            ),
            HeaderImage(
                "HeaderImageTitle",
                "http://image.superstreetonline.com/f/170287891+w660+h440+q80+re0+cr1+ar0+st0/2018-lexus-lc-500-hks-exhaust.jpg"
            ),
            "Desc"
        ),
        Footer(
            Author(
                "Auth",
                "AuthorHref"
            ),
            Date(2000, 1, 1, 1, 1, 1)
        ),
        CardSize.Large
    )

    @Test
    fun test() {
        // Given

        // When
        val result = testSubject.from(articlePreviewModel)

        // Then
        with(result) {
            magazineValue shouldEqual Magazine.SuperStreet.value
            typeValue shouldEqual Type.Feature.value
            headerValue shouldEqual "Title"
            headerUrl shouldEqual "TitleHref"
            headerImageTitle shouldEqual "HeaderImageTitle"
            headerImageUrl shouldEqual "http://image.superstreetonline.com/f/170287891+w600+h400+q80+re0+cr1+ar0+st0/2018-lexus-lc-500-hks-exhaust.jpg"
            headerDesc shouldEqual "Desc"
            authorValue shouldEqual "Auth"
            authorHref shouldEqual "AuthorHref"
        }
    }
}
