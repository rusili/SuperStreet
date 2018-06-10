package com.rusili.superstreet.ui.list

import com.rusili.superstreet.domain.list.ArticlePreviewModel

data class PreviewState(val position: Int,
                        val list: List<ArticlePreviewModel>?,
                        val error: Throwable? = null)