package com.nightrain.mediaselect.listener

import com.nightrain.mediaselect.entity.MediaEntity

interface LoadMediaCallback {
    fun loadSuccess(medias: MutableList<MediaEntity>)
}