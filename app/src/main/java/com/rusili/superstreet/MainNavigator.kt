package com.rusili.superstreet

import android.view.View

interface MainNavigator {

    fun goToArticle(
        view: View,
        href: String
    )
}
