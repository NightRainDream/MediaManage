package com.nightrain.mediaselect.util

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.nightrain.mediaselect.listener.ImageLoadSubject

object ImageLoadTool : ImageLoadSubject {
    private var mImageLoadSubject: ImageLoadSubject? = null


     fun init(imageLoadSubject: ImageLoadSubject) {
        mImageLoadSubject = imageLoadSubject
    }

    override fun loadImage(context: Context, uri: Uri, imageView: ImageView) {
        mImageLoadSubject?.loadImage(context, uri, imageView)
    }
}