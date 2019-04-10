package com.rusili.superstreet.database.favorites

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rusili.superstreet.common.models.Flag
import com.rusili.superstreet.common.models.Footer
import com.rusili.superstreet.common.models.Header
import com.rusili.superstreet.previewlist.domain.ArticlePreviewModel

@Entity
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    @Embedded val flag: Flag,
    @Embedded val header: Header,
    @Embedded val footer: Footer
)

object FavoriteEntityMapper {

    fun fromPreviewArticleModel(previewModel: ArticlePreviewModel) =
        FavoriteEntity(
            flag = previewModel.flag.copy(),
            header = previewModel.header.copy(),
            footer = previewModel.footer.copy()
        )
}
