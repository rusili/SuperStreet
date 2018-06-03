package com.rusili.superstreet.ui

interface MainNavigator {
    fun goToArticle(href: String)

    fun showError(error: Throwable?)
}