package com.rusili.superstreet.home

import android.view.View
import com.rusili.superstreet.common.models.Header

interface HomeNavigator {

    fun goToArticle(
        view: View,
        header: Header
    )
}
