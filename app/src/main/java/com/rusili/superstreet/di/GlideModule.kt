package com.rusili.superstreet.di

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import java.io.InputStream

@GlideModule
class GlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder
            .setLogLevel(Log.VERBOSE)
            .setDefaultRequestOptions(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .dontTransform()
                    .format(DecodeFormat.PREFER_RGB_565)
            )
    }
}
