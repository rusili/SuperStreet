package com.rusili.superstreet.domain.models

import com.rusili.superstreet.data.common.Magazine
import com.rusili.superstreet.data.common.Type

data class Flag(val magazine: Magazine,
                val type: Type
)