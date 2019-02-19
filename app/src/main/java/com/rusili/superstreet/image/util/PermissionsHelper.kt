package com.rusili.superstreet.image.util

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

private const val STORAGE_PERMISSION_REQUEST_CODE = 101

class PermissionsHelper {

    fun checkPermissionAndRequest(
        activity: Activity,
        permission: String
    ): Boolean {
        if (!checkPermission(activity, permission)) {
            requestPermission(activity, permission)
        }
        return checkPermission(activity, permission)
    }

    private fun checkPermission(
        activity: Activity,
        permission: String
    ): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
        }
        return true
    }

    private fun requestPermission(
        activity: Activity,
        permission: String
    ) {
        ActivityCompat.requestPermissions(activity, arrayOf(permission), STORAGE_PERMISSION_REQUEST_CODE);
    }
}
