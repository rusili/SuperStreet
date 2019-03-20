package com.rusili.superstreet.image.extensions

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

private const val STORAGE_PERMISSION_REQUEST_CODE = 101

fun Activity.checkPermissionAndRequest(permission: String): Boolean {
    if (!checkPermission(permission)) {
        requestPermission(permission)
    }
    return checkPermission(permission)
}

private fun Activity.checkPermission(permission: String): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }
    return true
}

private fun Activity.requestPermission(permission: String) {
    ActivityCompat.requestPermissions(this, arrayOf(permission), STORAGE_PERMISSION_REQUEST_CODE);
}
