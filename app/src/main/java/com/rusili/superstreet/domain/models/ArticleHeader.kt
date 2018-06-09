package com.rusili.superstreet.domain.models

import com.rusili.superstreet.domain.models.header.HeaderImageArticle
import com.rusili.superstreet.domain.models.header.Image
import com.rusili.superstreet.domain.models.header.Title

data class ArticleHeader(val title: Title,
                         val image: HeaderImageArticle,
                         val desc: String)