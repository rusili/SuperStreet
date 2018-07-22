package com.rusili.superstreet.ui.image

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.github.chrisbanes.photoview.PhotoView
import com.rusili.superstreet.R
import com.rusili.superstreet.ui.common.BUNDLE_KEY
import com.rusili.superstreet.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_image.*
import timber.log.Timber
import javax.inject.Inject
import com.rusili.superstreet.ui.image.util.PermissionsHelper
import android.content.Intent
import com.rusili.superstreet.ui.common.NoIntentException
import com.rusili.superstreet.ui.image.util.ImageSaver

class ImageActivity : BaseActivity() {
    @Inject lateinit var imageSaver: ImageSaver
    @Inject lateinit var permissionsHelper: PermissionsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition();
        setContentView(R.layout.activity_image)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onStart() {
        super.onStart()
        permissionsHelper.checkPermissionandRequestStorageAccess(this@ImageActivity)

        intent?.getStringExtra(BUNDLE_KEY)?.let {
            displayImage(it)
            setOnClickListeners(it)
        } ?: showError(NoIntentException())
    }

    private fun setOnClickListeners(imageHref: String) {
        activityImageSaveButton.setOnClickListener {
            ((activityImagePhotoView as PhotoView).drawable as? BitmapDrawable)?.let {
                saveImage(it, imageHref)
            }
        }

        activityImageShareButton.setOnClickListener {
            ((activityImagePhotoView as PhotoView).drawable as? BitmapDrawable)?.let {
                sendLinkIntent(imageHref)
            }
        }
    }

    private fun displayImage(href: String) {
        val requestOptions = RequestOptions().dontAnimate()

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

    private fun saveImage(it: BitmapDrawable,
                          imageHref: String) {
        if (permissionsHelper.checkPermissionandRequestStorageAccess(this@ImageActivity)) {
            imageSaver.saveImage(getContentResolver(), it.bitmap, imageHref)?.let {
                showSnackbar(getString(R.string.image_save_as) + imageHref)
            } ?: showSnackbar(getString(R.string.error_image_saving))
        }
    }

    private fun sendLinkIntent(imageHref: String) {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, imageHref)
            startActivity(Intent.createChooser(this, getString(R.string.share_link_message)))
        }
    }
}