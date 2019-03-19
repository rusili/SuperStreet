package com.rusili.superstreet.image

import android.Manifest
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.transition.Transition
import android.widget.ImageView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.chrisbanes.photoview.PhotoView
import com.rusili.superstreet.R
import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.ImageSize
import com.rusili.superstreet.common.ui.BaseActivity
import com.rusili.superstreet.common.ui.NoIntentException
import com.rusili.superstreet.image.extensions.checkPermissionAndRequest
import com.rusili.superstreet.image.extensions.saveImage
import com.rusili.superstreet.image.ui.SimpleRequestListener
import com.rusili.superstreet.image.ui.SimpleTransitionListenerAdapter
import io.reactivex.subjects.CompletableSubject
import kotlinx.android.synthetic.main.activity_image.*

private const val WRITE_EXTERNAL_STORAGE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE
private const val INTENT_TYPE_TEXT_PLAIN = "text/plain"

class ImageActivity : BaseActivity() {
    private val placeholderLoadSubject = CompletableSubject.create()
    private val fullImageLoadSubject = CompletableSubject.create()

    companion object {
        const val IMAGE_BUNDLE_KEY = "IMAGE_BUNDLE_KEY"
        const val IMAGE_SIZE_BUNDLE_KEY = "IMAGE_SIZE_BUNDLE_KEY"
        const val IMAGE_TRANSITION_NAME_KEY = "IMAGE_TRANSITION_NAME_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition();
        setContentView(R.layout.activity_image)
        checkPermissionAndRequest(WRITE_EXTERNAL_STORAGE_PERMISSION)

        setupViewSwitcher()
        retrieveIntent()
    }

    private fun setupViewSwitcher() {
        window.sharedElementEnterTransition.addListener(object : SimpleTransitionListenerAdapter() {
            override fun onTransitionEnd(transition: Transition?) {
                placeholderLoadSubject.onComplete()
            }
        })
        placeholderLoadSubject.mergeWith(fullImageLoadSubject)
            .subscribe {
                activityImageViewSwitcher.showNext()
            }
    }

    private fun retrieveIntent() {
        intent?.let {
            val image = it.getParcelableExtra<Image>(IMAGE_BUNDLE_KEY)
            val originalImageSize = it.getSerializableExtra(IMAGE_SIZE_BUNDLE_KEY) as ImageSize
            activityImageViewSwitcher.transitionName = it.getStringExtra(IMAGE_TRANSITION_NAME_KEY)

            image?.let {
                loadPlaceholderImage(it, originalImageSize)
                loadFullImage(it)
                ::setOnClickListeners
            }
        } ?: showError(NoIntentException())
    }

    private fun loadPlaceholderImage(
        image: Image,
        imageSize: ImageSize
    ) {
        Glide.with(this@ImageActivity)
            .load(if (imageSize == ImageSize.GROUP) image.resizeToGroupSize() else image.resizeToDefaultSize())
            .apply(RequestOptions().dontTransform().dontAnimate())
            .listener(object : SimpleRequestListener() {
                override fun onReadyOrFailed() {
                    supportStartPostponedEnterTransition()
                }
            })
            .into(activityImageViewSwitcher.currentView as ImageView)
    }

    private fun loadFullImage(image: Image) {
        activityImageViewSwitcher.nextView.isInvisible = true

        Glide.with(this@ImageActivity)
            .load(image.resizeTo1920By1280())
            .apply(RequestOptions().dontTransform().dontAnimate())
            .listener(object : SimpleRequestListener() {
                override fun onReadyOrFailed() {
                    activityImageProgressBar.isVisible = false
                    fullImageLoadSubject.onComplete()
                }
            })
            .into(activityImageViewSwitcher.nextView as PhotoView)
    }

    private fun setOnClickListeners(image: Image) {
        activityImageSaveButton.setOnClickListener {
            saveImage(
                (activityImagePhotoView as PhotoView).drawable as BitmapDrawable,
                image.resizeTo1920By1280()
            )
        }

        activityImageShareButton.setOnClickListener {
            sendLinkIntent(image.resizeTo1920By1280())
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
