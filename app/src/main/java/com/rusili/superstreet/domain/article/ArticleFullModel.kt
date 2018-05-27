package com.rusili.superstreet.domain.article

import com.rusili.superstreet.domain.common.Flag
import com.rusili.superstreet.domain.common.Footer
import com.rusili.superstreet.domain.common.Header

data class ArticleFullModel(val flag: Flag,
                            val header: Header,
                            val footer: Footer)