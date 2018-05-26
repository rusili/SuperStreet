package com.rusili.superstreet.domain.list

import com.rusili.superstreet.domain.common.Flag
import com.rusili.superstreet.domain.common.Footer
import com.rusili.superstreet.domain.common.Header

data class ArticlePreviewModel(val flag: Flag,
                               val header: Header,
                               val footer: Footer)