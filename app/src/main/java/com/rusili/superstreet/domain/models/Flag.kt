package com.rusili.superstreet.domain.models

import com.rusili.superstreet.domain.models.flag.Magazine
import com.rusili.superstreet.domain.models.flag.Type

data class Flag(val magazine: Magazine,
                val type: Type)