package com.rusili.superstreet.ui.util

data class LiveDataWrapper<T>(val data: T?,
                              val error: Throwable? = null)