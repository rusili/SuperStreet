package com.rusili.superstreet.ui.image

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.rusili.superstreet.R
import com.rusili.superstreet.domain.models.body.ImageGallery
import com.rusili.superstreet.domain.models.body.ImageSize
import com.rusili.superstreet.ui.common.BaseActivity
import com.rusili.superstreet.ui.common.NoIntentException
import com.rusili.superstreet.ui.image.util.ImageSaver
import com.rusili.superstreet.ui.image.util.PermissionsHelper
import kotlinx.android.synthetic.main.activity_image.*
import javax.inject.Inject

const val IMAGE_URL_BUNDLE_KEY = "IMAGE_URL_BUNDLE_KEY"
const val IMAGE_SIZE_BUNDLE_KEY = "IMAGE_SIZE_BUNDLE_KEY"

class ImageActivity : BaseActivity() {
    @Inject lateinit var imageSaver: ImageSaver
    @Inject lateinit var permissionsHelper: PermissionsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition();
        setContentView(R.layout.activity_image)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        permissionsHelper.checkPermissionandRequestStorageAccess(this@ImageActivity)

        val imageSize = intent?.getSerializableExtra(IMAGE_SIZE_BUNDLE_KEY) as ImageSize
        intent?.getParcelableExtra<ImageGallery>(IMAGE_URL_BUNDLE_KEY)?.let {
            initialDisplayImage(it, imageSize)
            setOnClickListeners(it)
        } ?: showError(NoIntentException())
    }

    private fun initialDisplayImage(image: ImageGallery,
                                    imageSize: ImageSize) {
        Glide.with(this)
                .load(image.resizeTo1920By1280())
                .thumbnail(Glide.with(this)
                        .load(if (imageSize == ImageSize.GROUP) image.resizeToGroupSize() else image.resizeToDefaultSize()))
                .into(activityImagePhotoView)

        supportStartPostponedEnterTransition()
    }

    private fun setOnClickListeners(image: ImageGallery) {
        activityImageSaveButton.setOnClickListener {
            ((activityImagePhotoView as PhotoView).drawable as? BitmapDrawable)?.let {
                saveImage(it, image.resizeTo1920By1280())
            }
        }

        activityImageShareButton.setOnClickListener {
            ((activityImagePhotoView as PhotoView).drawable as? BitmapDrawable)?.let {
                sendLinkIntent(image.resizeTo1920By1280())
            }
        }
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