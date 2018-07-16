package com.rusili.superstreet.ui.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
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
import com.rusili.superstreet.ui.util.ImageNameHelper
import kotlinx.android.synthetic.main.activity_image.*
import timber.log.Timber
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject

class ImageActivity : BaseActivity() {
    @Inject
    lateinit var imageHelper: ImageNameHelper

    private var imageHref: String? = null

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

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        activityImageSaveButton.setOnClickListener {
            ((activityImagePhotoView as PhotoView).drawable as? BitmapDrawable)?.let {
                saveImage(imageHref, it.bitmap)
            }
        }
    }

    private fun saveImage(imageHref: String?,
                          bitmap: Bitmap) {
        if (imageHref == null) return

        val imageName = imageHelper.getImageName(imageHref)
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = baseContext.openFileOutput(imageName, Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            showSnackbar("Error saving image")
        } catch (e: IOException) {
            e.printStackTrace()
            showSnackbar("Error saving image")
        } finally {
            fileOutputStream?.close()
            showSnackbar("Image saved as: " + imageName, Snackbar.LENGTH_LONG)
        }
    }

    private fun saveImage(bitmap: Bitmap) {
        String savedImagePath = null;

        String imageFileName = "JPEG_"+"FILE_NAME"+".jpg";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + "/YOUR_FOLDER_NAME");
        boolean success = true;
        if (!storageDir.exists()) {
            success = storageDir.mkdirs();
        }
        if (success) {
            File imageFile = new File(storageDir, imageFileName);
            savedImagePath = imageFile.getAbsolutePath();
            try {
                OutputStream fOut = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Add the image to the system gallery
            galleryAddPic(savedImagePath);
            Toast.makeText(mContext, "IMAGE SAVED", Toast.LENGTH_LONG).show();
        }
        return savedImagePath;
    }

    private fun galleryAddPic(imagePath: String) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imagePath);
        Uri contentUri = Uri . fromFile (f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
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
}