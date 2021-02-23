package com.nightrain.mediaselect.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nightrain.mediaselect.CustomTitleView
import com.nightrain.mediaselect.MediaSelectHelp
import com.nightrain.mediaselect.R
import com.nightrain.mediaselect.adapter.AudioAdapter
import com.nightrain.mediaselect.adapter.GIFAdapter
import com.nightrain.mediaselect.base.BaseActivity
import com.nightrain.mediaselect.base.KEY_TITLE_COLOR
import com.nightrain.mediaselect.custom.CustomGridLayoutItemDecoration
import com.nightrain.mediaselect.entity.MediaEntity
import com.nightrain.mediaselect.listener.LoadMediaCallback
import com.nightrain.mediaselect.util.ClickTool
import com.nightrain.mediaselect.util.MediaDataTool

class AudioSelectActivity : BaseActivity() {
    companion object {
        fun launchActivity(
            activity: Activity,
            titleBackGroundColor: Int = MediaSelectHelp.sTitleColor
        ) {
            if (ClickTool.isClick()) {
                val intent = Intent(activity, AudioSelectActivity::class.java)
                intent.putExtra(KEY_TITLE_COLOR, titleBackGroundColor)
                activity.startActivity(intent)
            }
        }

        fun launchActivity(
            fragment: Fragment,
            titleBackGroundColor: Int = MediaSelectHelp.sTitleColor
        ) {
            if (ClickTool.isClick()) {
                val intent = Intent(fragment.context, AudioSelectActivity::class.java)
                intent.putExtra(KEY_TITLE_COLOR, titleBackGroundColor)
                fragment.startActivity(intent)
            }
        }
    }

    private lateinit var rv_audio: RecyclerView
    private val mAudios = mutableListOf<MediaEntity>()
    private lateinit var mAudioAdapter: AudioAdapter
    override fun initUI(savedInstanceState: Bundle?): Int {
        return R.layout.activity_audio_select
    }

    override fun initView(savedInstanceState: Bundle?) {
        rv_audio = findViewById(R.id.rv_audio)
    }

    override fun initAdapter(savedInstanceState: Bundle?) {
        mAudioAdapter = AudioAdapter(mAudios)
        rv_audio.layoutManager = LinearLayoutManager(this)
        rv_audio.addItemDecoration(
            CustomGridLayoutItemDecoration(
                resources.getDimension(R.dimen.dp_10).toInt()
            )
        )
        rv_audio.adapter = mAudioAdapter
    }

    override fun initListener(savedInstanceState: Bundle?) {
        mAudioAdapter.setOnItemClickListener(object : AudioAdapter.OnItemClickListener {
            override fun onItemClick(entity: MediaEntity) {
                MediaSelectHelp.mAudioSelectCallback?.onSelectSuccess(entity)
                onBackPressed()
            }
        })
        mTitleView?.setOnTitleActionClickListener(object :
            CustomTitleView.OnTitleActionClickListener {
            override fun onBackClick() {
                onBackPressed()
            }

            override fun onMoreClick(v: View) {
                MediaSelectHelp.mAudioSelectCallback?.onSelectError(resources.getString(R.string.toast_cancel))
                onBackPressed()
            }
        })
    }

    override fun initData(savedInstanceState: Bundle?) {
        if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            loadData()
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (PERMISSION_REQUEST_CODE == requestCode) {
            if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                loadData()
            }
        }
    }

    private fun loadData() {
        MediaDataTool.loadAudioResources(this, object : LoadMediaCallback {
            override fun loadSuccess(medias: MutableList<MediaEntity>) {
                runOnUiThread {
                    mAudios.clear()
                    mAudios.addAll(medias)
                    mAudioAdapter.notifyDataSetChanged()
                }
            }
        })
    }

}