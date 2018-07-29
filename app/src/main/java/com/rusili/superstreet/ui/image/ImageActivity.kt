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
import com.rusili.superstreet.domain.models.body.ImageGallery
import com.rusili.superstreet.ui.common.NoIntentException
import com.rusili.superstreet.ui.image.util.ImageSaver
import android.R.transition
import android.transition.Transition
import androidx.transition.TransitionListenerAdapter


class ImageActivity : BaseActivity() {
    @Inject lateinit var imageSaver: ImageSaver
    @Inject lateinit var permissionsHelper: PermissionsHelper

    private lateinit var image: ImageGallery

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition();
        setContentView(R.layout.activity_image)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        permissionsHelper.checkPermissionandRequestStorageAccess(this@ImageActivity)

        intent?.getParcelableExtra<ImageGallery>(BUNDLE_KEY)?.let {
            image = it
            initialDisplayImage(it)
            setOnClickListeners(it)
        } ?: showError(NoIntentException())
    }

//    override fun onStart() {
//        super.onStart()
//
//        val sharedElementEnterTransition = window.sharedElementEnterTransition
//        sharedElementEnterTransition.addListener(object : TransitionListenerAdapter(), Transition.TransitionListener {
//            override fun onTransitionEnd(p0: Transition?) {
//                secondaryImageLoad(image)
//            }
//
//            override fun onTransitionResume(p0: Transition?) = Unit
//            override fun onTransitionPause(p0: Transition?) = Unit
//            override fun onTransitionCancel(p0: Transition?) = Unit
//            override fun onTransitionStart(p0: Transition?) = Unit
//        })
//    }

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

    private fun initialDisplayImage(image: ImageGallery) {
        Glide.with(this)
                .load(image.resizeTo1920By1280())
                .apply(RequestOptions().dontAnimate())
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

    private fun secondaryImageLoad(image: ImageGallery) {
        Glide.with(this)
                .load(image.resizeToGroupSize())
                .apply(RequestOptions().dontAnimate())
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