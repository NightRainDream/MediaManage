package com.nightrain.mediaselect.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nightrain.mediaselect.CustomTitleView
import com.nightrain.mediaselect.MediaSelectHelp
import com.nightrain.mediaselect.R
import com.nightrain.mediaselect.adapter.PictureAdapter
import com.nightrain.mediaselect.base.BaseActivity
import com.nightrain.mediaselect.base.KEY_TITLE_COLOR
import com.nightrain.mediaselect.custom.CustomGridLayoutItemDecoration
import com.nightrain.mediaselect.entity.MediaEntity
import com.nightrain.mediaselect.listener.LoadMediaCallback
import com.nightrain.mediaselect.listener.MediaSelectCallback
import com.nightrain.mediaselect.util.ClickTool
import com.nightrain.mediaselect.util.MediaDataTool

class PictureSelectActivity : BaseActivity() {

    companion object {
        fun launchActivity(
            activity: Activity,
            titleBackGroundColor: Int = MediaSelectHelp.sTitleColor,
            isDisplayGIF: Boolean = false
        ) {
            if (ClickTool.isClick()) {
                val intent = Intent(activity, PictureSelectActivity::class.java)
                intent.putExtra(KEY_TITLE_COLOR, titleBackGroundColor)
                intent.putExtra("isDisplayGIF", isDisplayGIF)
                activity.startActivity(intent)
            }
        }

        fun launchActivity(
            fragment: Fragment,
            titleBackGroundColor: Int = MediaSelectHelp.sTitleColor,
            isDisplayGIF: Boolean = false
        ) {
            if (ClickTool.isClick()) {
                val intent = Intent(fragment.context, PictureSelectActivity::class.java)
                intent.putExtra(KEY_TITLE_COLOR, titleBackGroundColor)
                intent.putExtra("isDisplayGIF", isDisplayGIF)
                fragment.startActivity(intent)
            }
        }
    }

    private lateinit var rv_picture: RecyclerView
    private lateinit var mPictureAdapter: PictureAdapter
    private val mPictures = mutableListOf<MediaEntity>()
    override fun initUI(savedInstanceState: Bundle?): Int {
        return R.layout.activity_picture_select
    }

    override fun initView(savedInstanceState: Bundle?) {
        rv_picture = findViewById(R.id.rv_picture)
    }

    override fun initAdapter(savedInstanceState: Bundle?) {
        mPictureAdapter = PictureAdapter(mPictures)
        rv_picture.layoutManager = GridLayoutManager(this, 2)
        rv_picture.addItemDecoration(
            CustomGridLayoutItemDecoration(
                resources.getDimension(R.dimen.dp_10).toInt()
            )
        )
        rv_picture.adapter = mPictureAdapter
    }

    override fun initListener(savedInstanceState: Bundle?) {
        mPictureAdapter.setOnItemClickListener(object : PictureAdapter.OnItemClickListener {
            override fun onItemClick(entity: MediaEntity) {
                MediaSelectHelp.mPictureSelectCallback?.onSelectSuccess(entity)
                onBackPressed()
            }
        })
        mTitleView?.setOnTitleActionClickListener(object :
            CustomTitleView.OnTitleActionClickListener {
            override fun onBackClick() {
                onBackPressed()
            }

            override fun onMoreClick(v: View) {
                MediaSelectHelp.mPictureSelectCallback?.onSelectError(resources.getString(R.string.toast_cancel))
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
        MediaDataTool.loadImageResources(this,intent.getBooleanExtra("isDisplayGIF",false), object : LoadMediaCallback {
            override fun loadSuccess(medias: MutableList<MediaEntity>) {
                runOnUiThread {
                    mPictures.clear()
                    mPictures.addAll(medias)
                    mPictureAdapter.notifyDataSetChanged()
                }
            }
        })
    }
}