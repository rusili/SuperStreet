package com.rusili.superstreet.database.favorites

import com.rusili.superstreet.common.models.Flag
import com.rusili.superstreet.common.models.Footer
import com.rusili.superstreet.common.models.Header
import com.rusili.superstreet.common.models.ImageUrl
import com.rusili.superstreet.common.models.Magazine
import com.rusili.superstreet.common.models.ModelMapper
import com.rusili.superstreet.common.models.Type
import com.rusili.superstreet.common.models.footer.Author
import com.rusili.superstreet.common.models.header.HeaderImage
import com.rusili.superstreet.common.models.header.Title
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel
import com.rusili.superstreet.previewlist.domain.CardSize
import java.util.Date

class FavoriteModelMapper :
    ModelMapper<FavoriteEntity, ArticlePreviewModel> {

    override fun to(t: FavoriteEntity): ArticlePreviewModel =
        ArticlePreviewModel(
            Flag(
                Magazine.fromString(t.magazineValue),
                Type.fromString(t.typeValue)
            ),
            Header(
                Title(
                    t.headerValue,
                    t.headerUrl
                ),
                HeaderImage(
                    t.headerImageTitle,
                    ImageUrl(t.headerImageUrl)
                ),
                t.headerDesc
            ),
            Footer(
                Author(
                    t.authorValue,
                    t.authorHref
                ),
                Date().apply {
                    time = t.date
                }
            ),
            CardSize.Small
        )

    override fun from(r: ArticlePreviewModel): FavoriteEntity =
        FavoriteEntity(
            magazineValue = r.flag.magazine.value,
            typeValue = r.flag.type.value,
            headerValue = r.header.title.value,
            headerUrl = r.header.title.href,
            headerImageTitle = r.header.headerImage.title,
            headerImageUrl = r.header.headerImage.getDefaultSizeUrl(),
            headerDesc = r.header.desc,
            authorValue = r.footer.author.value,
            authorHref = r.footer.author.href,
            date = r.footer.date.time
        )
}
