package com.nightrain.mediaselect.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nightrain.mediaselect.CustomTitleView
import com.nightrain.mediaselect.MediaSelectHelp
import com.nightrain.mediaselect.R
import com.nightrain.mediaselect.adapter.VideoAdapter
import com.nightrain.mediaselect.base.BaseActivity
import com.nightrain.mediaselect.base.KEY_TITLE_COLOR
import com.nightrain.mediaselect.custom.CustomGridLayoutItemDecoration
import com.nightrain.mediaselect.entity.MediaEntity
import com.nightrain.mediaselect.listener.LoadMediaCallback
import com.nightrain.mediaselect.listener.MediaSelectCallback
import com.nightrain.mediaselect.util.ClickTool
import com.nightrain.mediaselect.util.MediaDataTool

class VideoSelectActivity : BaseActivity() {

    companion object {
        fun launchActivity(
            activity: Activity,
            titleBackGroundColor: Int = MediaSelectHelp.sTitleColor
        ) {
            if (ClickTool.isClick()) {
                val intent = Intent(activity, VideoSelectActivity::class.java)
                intent.putExtra(KEY_TITLE_COLOR, titleBackGroundColor)
                activity.startActivity(intent)
            }
        }

        fun launchActivity(
            fragment: Fragment,
            titleBackGroundColor: Int = MediaSelectHelp.sTitleColor
        ) {
            if (ClickTool.isClick()) {
                val intent = Intent(fragment.context, VideoSelectActivity::class.java)
                intent.putExtra(KEY_TITLE_COLOR, titleBackGroundColor)
                fragment.startActivity(intent)
            }
        }
    }

    private lateinit var rv_picture: RecyclerView
    private lateinit var mVideoAdapter: VideoAdapter
    private val mPictures = mutableListOf<MediaEntity>()
    override fun initUI(savedInstanceState: Bundle?): Int {
        return R.layout.activity_video_select
    }

    override fun initView(savedInstanceState: Bundle?) {
        rv_picture = findViewById(R.id.rv_picture)
    }

    override fun initAdapter(savedInstanceState: Bundle?) {
        mVideoAdapter = VideoAdapter(mPictures)
        rv_picture.layoutManager = GridLayoutManager(this, 2)
        rv_picture.addItemDecoration(
            CustomGridLayoutItemDecoration(
                resources.getDimension(R.dimen.dp_10).toInt()
            )
        )
        rv_picture.adapter = mVideoAdapter
    }

    override fun initListener(savedInstanceState: Bundle?) {
        mVideoAdapter.setOnItemClickListener(object : VideoAdapter.OnItemClickListener {
            override fun onItemClick(entity: MediaEntity) {
                MediaSelectHelp.mVideoSelectCallback?.onSelectSuccess(entity)
                onBackPressed()
            }
        })
        mTitleView?.setOnTitleActionClickListener(object :
            CustomTitleView.OnTitleActionClickListener {
            override fun onBackClick() {
                onBackPressed()
            }

            override fun onMoreClick(v: View) {
                MediaSelectHelp.mVideoSelectCallback?.onSelectError(resources.getString(R.string.toast_cancel))
                onBackPressed()
            }
        })
    }

    override fun initData(savedInstanceState: Bundle?) {
        MediaDataTool.loadVideoResources(this, object : LoadMediaCallback {
            override fun loadSuccess(medias: MutableList<MediaEntity>) {
                runOnUiThread {
                    mPictures.clear()
                    mPictures.addAll(medias)
                    mVideoAdapter.notifyDataSetChanged()
                }
            }
        })
    }

}