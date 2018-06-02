package com.rusili.superstreet.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity

const val BUNDLE_KEY = "BUNDLE_KEY"

fun <T> AppCompatActivity.GoToActivity(clazz: Class<T>,
                                       value: String? = null) {
    val intent = Intent(this, clazz)
    intent.putExtra(BUNDLE_KEY, value)
    startActivity(intent)
}