package com.nightrain.mediaselect.listener

import com.nightrain.mediaselect.entity.MediaEntity

interface MediaSelectCallback {
    fun onSelectSuccess(entity: MediaEntity)

    fun onSelectError(error: String)
}