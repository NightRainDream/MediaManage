package com.nightrain.mediaselect.util

object ClickTool {
    private val MIN_CLICK_DELAY_TIME = 500
    private var mLasClickTime = 0L
    fun isClick(): Boolean {
        var flag = false
        val curClickTime = System.currentTimeMillis()
        if ((curClickTime - mLasClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true
        }
        mLasClickTime = curClickTime
        return flag
    }
}