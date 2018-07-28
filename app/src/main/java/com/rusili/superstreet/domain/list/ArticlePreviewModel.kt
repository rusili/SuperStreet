package com.rusili.superstreet.domain.list

import androidx.room.Entity
import com.rusili.superstreet.domain.models.Flag
import com.rusili.superstreet.domain.models.Footer
import com.rusili.superstreet.domain.models.Header

@Entity(tableName = "articlePreviews")
data class ArticlePreviewModel(val flag: Flag,
                               val header: Header,
                               val footer: Footer)