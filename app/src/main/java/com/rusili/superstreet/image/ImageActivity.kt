package com.rusili.superstreet.image

import android.Manifest
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.WindowManager
import android.view.animation.Interpolator
import androidx.core.view.isVisible
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.github.chrisbanes.photoview.PhotoView
import com.rusili.superstreet.R
import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.ImageSize
import com.rusili.superstreet.common.ui.BaseActivity
import com.rusili.superstreet.common.ui.NoIntentException
import com.rusili.superstreet.image.extensions.checkPermissionAndRequest
import com.rusili.superstreet.image.extensions.saveImage
import kotlinx.android.synthetic.main.activity_image.*
import timber.log.Timber

private const val WRITE_EXTERNAL_STORAGE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE
private const val INTENT_TYPE_TEXT_PLAIN = "text/plain"

class ImageActivity : BaseActivity() {

    companion object {
        const val IMAGE_URL_BUNDLE_KEY = "IMAGE_URL_BUNDLE_KEY"
        const val IMAGE_SIZE_BUNDLE_KEY = "IMAGE_SIZE_BUNDLE_KEY"
        const val IMAGE_TRANSITION_NAME = "IMAGE_TRANSITION_NAME_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        getWindow().sharedElementEnterTransition = TransitionInflater.from(this)
            .inflateTransition(R.transition.view_sharedelement_slide)

        checkPermissionAndRequest(WRITE_EXTERNAL_STORAGE_PERMISSION)

        intent?.let {
            val imageSize = it.getSerializableExtra(IMAGE_SIZE_BUNDLE_KEY) as ImageSize
            it.getParcelableExtra<Image>(IMAGE_URL_BUNDLE_KEY)?.let {
                initialDisplayImage(it, imageSize)
                setOnClickListeners(it)
            }
            activityImagePhotoView.transitionName = it.getStringExtra(IMAGE_TRANSITION_NAME)
        } ?: showError(NoIntentException())
    }

    private fun initialDisplayImage(
        image: Image,
        imageSize: ImageSize
    ) {
        val listener = object : RequestListener<Drawable> {
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                activityImageProgressBar.isVisible = false
                supportStartPostponedEnterTransition()
                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Timber.e(e, "Error loading full image: %s", image.resizeToDefaultSize())
                activityImageProgressBar.isVisible = false
                supportStartPostponedEnterTransition()
                return false
            }
        }

        Glide.with(this@ImageActivity)
            .load(
                if (imageSize == ImageSize.GROUP) image.resizeToGroupSize() else image.resizeToDefaultSize()
            )
//            .thumbnail(
//                Glide.with(this)
//                    .load(if (imageSize == ImageSize.GROUP) image.resizeToGroupSize() else image.resizeToDefaultSize())
//            )
            .listener(listener)
            .into(activityImagePhotoView)
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
