package com.rusili.superstreet.ui.image

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.github.chrisbanes.photoview.PhotoView
import com.google.android.material.snackbar.Snackbar
import com.rusili.superstreet.R
import com.rusili.superstreet.ui.common.BUNDLE_KEY
import com.rusili.superstreet.ui.common.BaseActivity
import com.rusili.superstreet.ui.util.ImageNameHelper
import kotlinx.android.synthetic.main.activity_image.*
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class ImageActivity : BaseActivity() {
    @Inject
    lateinit var imageHelper: ImageNameHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition();
        setContentView(R.layout.activity_image)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onStart() {
        super.onStart()

        intent?.getStringExtra(BUNDLE_KEY)?.let {
            displayImage(it)
            setOnClickListeners(it)
        } ?: showErrorDialogToFinish()
    }

    private fun setOnClickListeners(imageHref: String) {
        activityImageSaveButton.setOnClickListener {
            ((activityImagePhotoView as PhotoView).drawable as? BitmapDrawable)?.let {
                saveImage(imageHref, it.bitmap)
            }
        }
    }

    private fun displayImage(href: String) {
        val requestOptions = RequestOptions().placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.ic_error_outline_black_24dp)
                .dontAnimate()

        Glide.with(this)
                .load(href)
                .apply(requestOptions)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        Timber.e(e, "Error loading image into ImageActivity")
                        supportStartPostponedEnterTransition()
                        return false
                    }
                })
                .into(activityImagePhotoView)
    }

    private fun saveImage(imageHref: String,
                          bitmap: Bitmap) {
        val imageFileName = imageHelper.getImageName(imageHref)
        val storageDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString())
        if (!storageDir.exists()) return

        val imageFile = File(storageDir, imageFileName)
        val savedImagePath = imageFile.getAbsolutePath()
        try {
            val fileOut = FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOut)
            fileOut.close()
        } catch (e: Exception) {
            e.printStackTrace()
            showSnackbar("Error saving image")
        }
        addToGalley(savedImagePath);
        showSnackbar("Image saved as: " + imageFileName, Snackbar.LENGTH_LONG)
    }

    private fun addToGalley(imagePath: String) {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).apply {
            val file = File(imagePath)
            val contentUri = Uri.fromFile(file)
            setData(contentUri)
        }
        sendBroadcast(mediaScanIntent)
    }
}