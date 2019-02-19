package com.rusili.superstreet.common.ui

data class LiveDataWrapper<T>(
    val data: T?,
    val error: Throwable? = null
)
