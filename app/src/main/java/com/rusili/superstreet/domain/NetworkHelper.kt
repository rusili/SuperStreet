package com.rusili.superstreet.domain

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

class NetworkHelper {

    fun isConnected(context: Context) =
            with(context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager) {
                this.activeNetworkInfo?.isConnected ?: false
            }
}