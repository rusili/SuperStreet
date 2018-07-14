package com.rusili.superstreet.ui.util

import java.util.*

class ImageNameHelper {

    fun getImageName(url: String): String {
        val indexOfLastBackslash = url.lastIndexOf('/')

        val removeFileType = url.removeRange(url.length - 4, url.length)
        val removeDomain = removeFileType.removeRange(0, indexOfLastBackslash + 1)

        return removeDomain + Date().time / 1000
    }
}