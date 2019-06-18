package com.rusili.superstreet.common.extensions

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.rusili.superstreet.jsoup.api.BASE_HTML

private const val STRING_SHARE_TYPE = "text/plain"
private const val STRING_SHARE_SUBJECT = "Sharing URL"
private const val STRING_SHARE_VIA = "Share via: "

fun Context?.isNetworkConnected(): Boolean =
    this?.let {
        (it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo?.isConnected ?: false
    } ?: false

fun Context?.shareLink(link: String) {
    this?.startActivity(
        Intent.createChooser(
            Intent(Intent.ACTION_SEND).apply {
                setType(STRING_SHARE_TYPE)
                putExtra(
                    Intent.EXTRA_SUBJECT,
                    STRING_SHARE_SUBJECT
                )
                putExtra(Intent.EXTRA_TEXT, BASE_HTML.plus(link))
            },
            STRING_SHARE_VIA
        )
    )
}
