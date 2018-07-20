package com.rusili.superstreet.ui.image.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

private const val STORAGE_PERMISSION_REQUEST_CODE = 101
private const val WRITE_EXTERNAL_STORAGE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE

class PermissionsHelper {

    fun checkPermissionandRequestStorageAccess(activity: Activity) =
            checkPermissionAndRequest(activity, WRITE_EXTERNAL_STORAGE_PERMISSION)

    private fun checkPermissionAndRequest(activity: Activity,
                                          permission: String): Boolean {
        if (!checkPermission(activity, permission)) {
            requestPermission(activity, permission)
        }
        return checkPermission(activity, permission)
    }

    private fun checkPermission(activity: Activity,
                                permission: String): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val result = activity.checkSelfPermission(permission)
            return result == PackageManager.PERMISSION_GRANTED
        }
        return true
    }

    private fun requestPermission(activity: Activity,
                                  permission: String) {
        ActivityCompat.requestPermissions(activity, arrayOf(permission), STORAGE_PERMISSION_REQUEST_CODE);
    }
}