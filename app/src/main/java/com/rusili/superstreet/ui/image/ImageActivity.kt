package com.rusili.superstreet.ui.image

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.ui.common.BUNDLE_KEY
import com.rusili.superstreet.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_image.*
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.rusili.superstreet.R
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.bumptech.glide.request.target.Target
import timber.log.Timber

class ImageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition();
        setContentView(R.layout.activity_image)

        val requestOptions = RequestOptions().placeholder(R.drawable.bg_placeholder)
                .error(R.drawable.ic_error_outline_black_24dp)
                .dontAnimate()

        intent.getStringExtra(BUNDLE_KEY)?.let { href ->
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
                    .into(activityPhotoView)
        }
    }
}