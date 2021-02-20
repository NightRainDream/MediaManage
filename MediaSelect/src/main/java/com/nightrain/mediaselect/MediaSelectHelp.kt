package com.nightrain.mediaselect

import android.app.Activity
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.fragment.app.Fragment
import com.nightrain.mediaselect.listener.ImageLoadSubject
import com.nightrain.mediaselect.listener.MediaSelectCallback
import com.nightrain.mediaselect.ui.GIFSelectActivity
import com.nightrain.mediaselect.ui.PictureSelectActivity
import com.nightrain.mediaselect.ui.VideoSelectActivity
import com.nightrain.mediaselect.util.ImageLoadTool

object MediaSelectHelp {
    var sTitleColor = Color.BLACK
    var mPictureSelectCallback: MediaSelectCallback? = null
    var mGIFSelectCallback: MediaSelectCallback? = null
    var mVideoSelectCallback: MediaSelectCallback? = null

    /**
     * 媒体选择库初始化
     * @param imageLoadSubject 图片加载代理库
     * @param titleBackgroundColor 标题栏以及状态栏背景颜色
     */
    fun init(imageLoadSubject: ImageLoadSubject, titleBackgroundColor: Int = Color.BLACK) {
        ImageLoadTool.init(imageLoadSubject)
        sTitleColor = titleBackgroundColor
    }

    class BuildSelectPicture {
        private var mTitleBackgroundColor = Color.BLACK

        fun setTitleBackGroundColor(@ColorInt titleColor: Int): BuildSelectPicture {
            this.mTitleBackgroundColor = titleColor
            return this
        }

        fun launch(activity: Activity, callback: MediaSelectCallback) {
            mPictureSelectCallback = callback
            PictureSelectActivity.launchActivity(activity, mTitleBackgroundColor)
        }

        fun launch(fragment: Fragment, callback: MediaSelectCallback) {
            mPictureSelectCallback = callback
            PictureSelectActivity.launchActivity(fragment, mTitleBackgroundColor)
        }
    }

    class BuildSelectGIF {
        private var mTitleBackgroundColor = Color.BLACK

        fun setTitleBackGroundColor(@ColorInt titleColor: Int): BuildSelectGIF {
            this.mTitleBackgroundColor = titleColor
            return this
        }

        fun launch(activity: Activity, callback: MediaSelectCallback) {
            mGIFSelectCallback = callback
            GIFSelectActivity.launchActivity(activity, mTitleBackgroundColor)
        }

        fun launch(fragment: Fragment, callback: MediaSelectCallback) {
            mGIFSelectCallback = callback
            GIFSelectActivity.launchActivity(fragment, mTitleBackgroundColor)
        }
    }

    class BuildSelectVideo {
        private var mTitleBackgroundColor = Color.BLACK

        fun setTitleBackGroundColor(@ColorInt titleColor: Int): BuildSelectVideo {
            this.mTitleBackgroundColor = titleColor
            return this
        }

        fun launch(activity: Activity, callback: MediaSelectCallback) {
            mVideoSelectCallback = callback
            VideoSelectActivity.launchActivity(activity, mTitleBackgroundColor)
        }

        fun launch(fragment: Fragment, callback: MediaSelectCallback) {
            mVideoSelectCallback = callback
            VideoSelectActivity.launchActivity(fragment, mTitleBackgroundColor)
        }
    }
}