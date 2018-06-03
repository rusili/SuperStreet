package com.rusili.superstreet.ui.common

data class LiveDataWrapper<T>(val data: T?,
                              val error: Throwable? = null)