package com.rusili.superstreet.ui.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.github.chrisbanes.photoview.PhotoView
import com.google.android.material.snackbar.Snackbar
import com.rusili.superstreet.R
import com.rusili.superstreet.ui.common.BUNDLE_KEY
import com.rusili.superstreet.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_image.*
import timber.log.Timber
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class ImageActivity : BaseActivity() {
    private var imageHref: String? = null
    private lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition();
        setContentView(R.layout.activity_image)

        intent?.getStringExtra(BUNDLE_KEY)?.let {
            imageHref = it
            displayImage(it)
        }
    }

    override fun onStart() {
        super.onStart()

        activityImageSaveButton.setOnClickListener {
            ((activityImagePhotoView as PhotoView).drawable as? BitmapDrawable)?.let {
                saveImage(it.bitmap)
            }
        }
    }

    private fun saveImage(bitmap: Bitmap) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = baseContext.openFileOutput(imageHref, Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            showSnackbar("Error saving image")
        } catch (e: IOException) {
            e.printStackTrace()
            showSnackbar("Error saving image")
        } finally {
            fileOutputStream?.close()
            showSnackbar("Image saved")
        }
    }

    private fun displayImage(href: String) {
        val requestOptions = RequestOptions().placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.ic_error_outline_black_24dp)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .dontAnimate()

        Glide.with(this)
                .asBitmap()
                .load(href)
                .apply(requestOptions)
                .listener(object : RequestListener<Bitmap> {
                    override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                        Timber.e(e, "Error loading image into ImageActivity")
                        supportStartPostponedEnterTransition()
                        return false
                    }
                })
                .into(activityImagePhotoView)
    }
}