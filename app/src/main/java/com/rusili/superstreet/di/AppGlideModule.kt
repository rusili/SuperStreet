package com.rusili.superstreet.di

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.rusili.superstreet.R

/**
 * Sets Glide default configuration for every Glide use.
 * NOT a dagger module
 */
@GlideModule
class AppGlideModule : AppGlideModule() {

    override fun applyOptions(
        context: Context,
        builder: GlideBuilder
    ) {
        builder.setDefaultRequestOptions(
            RequestOptions()
                .error(R.drawable.ic_error_outline_black_24dp))
    }
}
