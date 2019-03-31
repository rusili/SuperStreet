package com.rusili.superstreet

import android.view.View
import com.rusili.superstreet.common.models.Header

interface MainNavigator {

    fun goToArticle(
        view: View,
        header: Header,
        position: Int
    )
}
