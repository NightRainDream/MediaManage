package com.nightrain.mediaselect.listener

import android.content.Context
import android.net.Uri
import android.widget.ImageView

interface ImageLoadSubject {
    fun loadImage(context: Context,uri: Uri,imageView: ImageView)
}