package com.rusili.superstreet.image

import android.Manifest
import android.content.Intent
import android.content.IntentSender
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.transition.Transition
import android.widget.ImageView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.rusili.superstreet.R
import com.rusili.superstreet.common.base.BaseActivity
import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.Image.Companion.IMAGE_HIGHRES_HEIGHT
import com.rusili.superstreet.common.models.body.Image.Companion.IMAGE_HIGHRES_WIDTH
import com.rusili.superstreet.common.models.body.ImageSize
import com.rusili.superstreet.common.ui.SimpleRequestListener
import com.rusili.superstreet.common.ui.SimpleTransitionListenerAdapter
import com.rusili.superstreet.image.extensions.checkPermissionAndRequest
import com.rusili.superstreet.image.extensions.saveImage
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
        disposable.add(placeholderLoadSubject
            .mergeWith(fullImageLoadSubject)
            .subscribe {
                activityImageViewSwitcher.showNext()
            })
    }

    private fun retrieveIntent() {
        intent?.let {
            val image = it.getParcelableExtra<Image>(IMAGE_BUNDLE_KEY)
            val originalImageSize = it.getSerializableExtra(IMAGE_SIZE_BUNDLE_KEY) as ImageSize

            activityImageViewSwitcher.apply {
                transitionName = it.getStringExtra(IMAGE_TRANSITION_NAME_KEY)
                nextView.isInvisible = true
            }

            image?.let {
                loadPlaceholderImage(it, originalImageSize)
                loadFullImage(it)
                setOnClickListeners(it)
            }
        } ?: showError(IntentSender.SendIntentException())
    }

    private fun loadPlaceholderImage(
        image: Image,
        imageSize: ImageSize
    ) {
        Glide.with(this)
            .load(if (imageSize == ImageSize.GROUP) image.getGroupSizeUrl() else image.getDefaultSizeUrl())
            .listener(object : SimpleRequestListener() {
                override fun onReadyOrFailed() {
                    supportStartPostponedEnterTransition()
                }
            })
            .into(activityImageViewSwitcher.currentView as ImageView)
    }

    private fun loadFullImage(image: Image) {
        Glide.with(this)
            .load(image.getHighResUrl())
            .listener(object : SimpleRequestListener() {
                override fun onReadyOrFailed() {
                    activityImageProgressBar.isVisible = false
                    fullImageLoadSubject.onComplete()
                }
            })
            .override(IMAGE_HIGHRES_WIDTH, IMAGE_HIGHRES_HEIGHT)
            .into(activityImageViewSwitcher.nextView as PhotoView)
    }

    private fun setOnClickListeners(image: Image) {
        activityImageBack.setOnClickListener { finish() }

        activityImageSaveButton.setOnClickListener {
            saveImage(
                image = (activityImagePhotoView as PhotoView).drawable as BitmapDrawable,
                imageHref = image.getHighResUrl()
            )
        }

        activityImageShareButton.setOnClickListener {
            sendLinkIntent(image.getHighResUrl())
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
