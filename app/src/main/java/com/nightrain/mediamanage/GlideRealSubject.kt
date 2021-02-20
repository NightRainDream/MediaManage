package com.nightrain.mediamanage

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.nightrain.mediaselect.listener.ImageLoadSubject

class GlideRealSubject: ImageLoadSubject {
    override fun loadImage(context: Context, uri: Uri, imageView: ImageView) {
        Glide.with(context)
            .load(uri)
            .into(imageView)
    }
}