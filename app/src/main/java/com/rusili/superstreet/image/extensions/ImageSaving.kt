package com.rusili.superstreet.image.extensions

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.provider.MediaStore
import android.provider.MediaStore.Images
import androidx.annotation.VisibleForTesting
import timber.log.Timber

/**
 * Android internals have been modified to store images in the media folder with
 * the correct date meta data
 * @author samuelkirton
 * https://gist.github.com/samkirton/0242ba81d7ca00b475b9
 */

private const val MIME_TYPE_DIR = "image/png"
private val COMPRESS_FORMAT = Bitmap.CompressFormat.PNG

/**
 * A copy of the Android internals  insertImage method, this method populates the
 * meta data with DATE_ADDED and DATE_TAKEN. This fixes a common problem where media
 * that is inserted manually gets saved at the end of the gallery (because date is not populated).
 * @see android.provider.MediaStore.Images.Media.insertImage
 */
fun ContentResolver.saveImage(
    source: Bitmap,
    fullUrl: String,
    description: String? = null
): String? {
    val values = ContentValues().apply {
        put(Images.Media.TITLE, fullUrl.parseImageName())
        put(Images.Media.DISPLAY_NAME, fullUrl.parseImageName())
        put(Images.Media.DESCRIPTION, description)
        put(Images.Media.MIME_TYPE, MIME_TYPE_DIR)
        // Add the date meta data to ensure the image is added at the front of the gallery
        put(Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
        put(Images.Media.DATE_TAKEN, System.currentTimeMillis())
    }

    var url: Uri? = null
    try {
        url = insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        openOutputStream(url).use {
            source.compress(COMPRESS_FORMAT, 100, it)
        }
        ContentUris.parseId(url).let { id ->
            // Wait until MINI_KIND thumbnail is generated.
            val miniThumb = Images.Thumbnails.getThumbnail(this, id, Images.Thumbnails.MINI_KIND, null)
            // This is for backward compatibility.
            storeThumbnail(miniThumb, id, 50f, 50f, Images.Thumbnails.MICRO_KIND)
        }
    } catch (e: Exception) {
        Timber.e(e, "Error saving image")
        url?.let {
            delete(it, null, null)
            url = null
        }
    }

    return url?.toString()
}

/**
 * A copy of the Android internals StoreThumbnail method, it used with the insertImage to
 * populate the android.provider.MediaStore.Images.Media#insertImage with all the correct
 * meta data. The StoreThumbnail method is private so it must be duplicated here.
 * @see android.provider.MediaStore.Images.Media
 */
fun ContentResolver.storeThumbnail(
    source: Bitmap,
    id: Long,
    width: Float,
    height: Float,
    kind: Int
): Bitmap? {
    val matrix = Matrix().apply {
        setScale(
            width / source.width,
            height / source.height
        )
    }

    val thumb = Bitmap.createBitmap(
        source, 0, 0,
        source.width,
        source.height,
        matrix, true
    )

    val values = ContentValues(4).apply {
        put(Images.Thumbnails.KIND, kind)
        put(Images.Thumbnails.IMAGE_ID, id.toInt())
        put(Images.Thumbnails.HEIGHT, thumb.height)
        put(Images.Thumbnails.WIDTH, thumb.width)
    }

    val url = insert(Images.Thumbnails.EXTERNAL_CONTENT_URI, values)
        try {
        val thumbOut = openOutputStream(url)
        thumb.compress(COMPRESS_FORMAT, 80, thumbOut)
        thumbOut.close()
        return thumb
    } catch (e: Exception) {
        Timber.e(e, "Error creating thumbnail for image")
        return null
    }
}

/**
 * Last 4 characters of any image path should be:
 * .png, .jpg, etc
 */
@VisibleForTesting
fun String.parseImageName(): String =
    lastIndexOf('/')
        .takeIf { it > 0 }
        ?.let { substring(it + 1, length - 4) }
        ?: this
