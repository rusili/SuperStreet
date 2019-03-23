package com.rusili.superstreet.common.extensions

import android.content.Context
import android.net.ConnectivityManager

fun Context?.isNetworkConnected(): Boolean =
    this?.let {
        (it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo?.isConnected ?: false
    } ?: false
