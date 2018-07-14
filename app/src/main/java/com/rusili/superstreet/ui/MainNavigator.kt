package com.rusili.superstreet.ui

import android.view.View

interface MainNavigator {
    fun goToArticle(view: View,
                    href: String)

    fun showError(error: Throwable?)
}