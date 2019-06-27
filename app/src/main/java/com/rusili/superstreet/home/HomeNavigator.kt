package com.rusili.superstreet.home

import android.content.Intent
import android.view.View
import com.rusili.superstreet.common.models.Header

interface HomeNavigator {

    fun goToArticle(
        view: View,
        header: Header
    )

    fun shareLink(link: String)
}
