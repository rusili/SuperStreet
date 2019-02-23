package com.rusili.superstreet.image

import android.Manifest
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.rusili.superstreet.R
import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.ImageSize
import com.rusili.superstreet.common.ui.BaseActivity
import com.rusili.superstreet.common.ui.NoIntentException
import com.rusili.superstreet.image.extensions.checkPermissionAndRequest
import com.rusili.superstreet.image.extensions.saveImage
import kotlinx.android.synthetic.main.activity_image.*

private const val WRITE_EXTERNAL_STORAGE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE
private const val INTENT_TYPE_TEXT_PLAIN = "text/plain"

class ImageActivity : BaseActivity() {

    companion object {
        const val IMAGE_URL_BUNDLE_KEY = "IMAGE_URL_BUNDLE_KEY"
        const val IMAGE_SIZE_BUNDLE_KEY = "IMAGE_SIZE_BUNDLE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition();
        setContentView(R.layout.activity_image)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        checkPermissionAndRequest(WRITE_EXTERNAL_STORAGE_PERMISSION)

        val imageSize = intent?.getSerializableExtra(IMAGE_SIZE_BUNDLE_KEY) as ImageSize
        intent?.getParcelableExtra<Image>(IMAGE_URL_BUNDLE_KEY)?.let {
            initialDisplayImage(it, imageSize)
            setOnClickListeners(it)
        } ?: showError(NoIntentException())
    }

    private fun initialDisplayImage(
        image: Image,
        imageSize: ImageSize
    ) {
        Glide.with(this@ImageActivity)
            .load(image.resizeTo1920By1280())
            .thumbnail(Glide.with(this)
                .load(if (imageSize == ImageSize.GROUP) image.resizeToGroupSize() else image.resizeToDefaultSize()))
            .into(activityImagePhotoView)

        supportStartPostponedEnterTransition()
    }

    private fun setOnClickListeners(image: Image) {
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

    private fun saveImage(
        image: BitmapDrawable,
        imageHref: String
    ) {
        if (checkPermissionAndRequest(WRITE_EXTERNAL_STORAGE_PERMISSION)) {
            contentResolver.saveImage(image.bitmap, imageHref)?.let {
                showSnackbar(getString(R.string.image_save_as) + imageHref)
            } ?: showSnackbar(getString(R.string.error_image_saving))
        }
    }

    private fun sendLinkIntent(imageHref: String) {
        Intent(Intent.ACTION_SEND).apply {
            type = INTENT_TYPE_TEXT_PLAIN
            putExtra(Intent.EXTRA_TEXT, imageHref)
            startActivity(Intent.createChooser(this, getString(R.string.share_link_message)))
        }
    }
}
