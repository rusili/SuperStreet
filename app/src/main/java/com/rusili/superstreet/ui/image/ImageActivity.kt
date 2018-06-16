package com.rusili.superstreet.ui.image

import android.os.Bundle
import com.bumptech.glide.Glide
import com.rusili.superstreet.R
import com.rusili.superstreet.ui.common.BUNDLE_KEY
import com.rusili.superstreet.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        intent.getStringExtra(BUNDLE_KEY)?.let { href ->
            Glide.with(this)
                    .load(href)
                    .into(activity_photoView)
        }
    }
}