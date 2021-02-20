package com.nightrain.mediaselect.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nightrain.mediaselect.CustomTitleView
import com.nightrain.mediaselect.MediaSelectHelp
import com.nightrain.mediaselect.R

const val KEY_TITLE_COLOR = "titleBackGroundColor"

abstract class BaseActivity : AppCompatActivity() {
    open var mTitleView: CustomTitleView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initUI(savedInstanceState))
        initView(savedInstanceState)
        initSet(savedInstanceState)
        initAdapter(savedInstanceState)
        initListener(savedInstanceState)
        initData(savedInstanceState)
    }

    abstract fun initUI(savedInstanceState: Bundle?): Int
    abstract fun initView(savedInstanceState: Bundle?)
    open fun initSet(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        mTitleView = findViewById<CustomTitleView>(R.id.custom_title)
        val titleColor = intent.getIntExtra(KEY_TITLE_COLOR, MediaSelectHelp.sTitleColor)
        mTitleView?.setTitleBackgroundColor(titleColor)
        window.statusBarColor = titleColor
    }

    abstract fun initAdapter(savedInstanceState: Bundle?)
    abstract fun initListener(savedInstanceState: Bundle?)
    abstract fun initData(savedInstanceState: Bundle?)
}