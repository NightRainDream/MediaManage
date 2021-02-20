package com.nightrain.mediamanage

import android.app.Application
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.nightrain.mediaselect.MediaSelectHelp

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MediaSelectHelp.init(GlideRealSubject())
    }
}