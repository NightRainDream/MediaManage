package com.nightrain.mediaselect

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

private const val TAG = "CustomTitleView"

class CustomTitleView : RelativeLayout {
    private lateinit var tv_custom_title: TextView
    private lateinit var iv_custom_back: ImageView
    private lateinit var tv_custom_cancel: TextView
    private var mTitleText = ""
    private var mTitleMoreText = "取消"
    private var mTitleBackIcon: Drawable? = null
    private var mTitleBackgroundColor = Color.BLACK

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributes: AttributeSet?) : this(context, attributes, 0)
    constructor(context: Context, attributes: AttributeSet?, defaultAttr: Int) : super(
        context,
        attributes,
        defaultAttr
    ) {
        initUI(context)
        initAttrs(context, attributes)
    }

    private fun initAttrs(context: Context, attributes: AttributeSet?) {
        val attrs = context.obtainStyledAttributes(attributes, R.styleable.CustomTitleView)
        val titleText = attrs.getString(R.styleable.CustomTitleView_customTitleText)
        if (!TextUtils.isEmpty(titleText)) {
            mTitleText = titleText!!
        }
        val moreText = attrs.getString(R.styleable.CustomTitleView_customTitleMoreText)
        if (!TextUtils.isEmpty(moreText)) {
            mTitleMoreText = moreText!!
        }
        mTitleBackgroundColor = attrs.getColor(
            R.styleable.CustomTitleView_customTitleBackgroundColor,
            mTitleBackgroundColor
        )
        attrs.getDrawable(R.styleable.CustomTitleView_customTitleBackIcon)?.let {
            mTitleBackIcon = it
        }
        if (mTitleBackIcon == null) {
            mTitleBackIcon =
                ContextCompat.getDrawable(context, R.drawable.ic_baseline_arrow_back_24)
        }
        attrs.recycle()
        upDataIconColor()
        tv_custom_title.text = mTitleText
        tv_custom_cancel.text = mTitleMoreText

    }

    private fun upDataIconColor() {
        val iconColor = getIconColor(mTitleBackgroundColor)
        tv_custom_title.setTextColor(iconColor)
        tv_custom_cancel.setTextColor(iconColor)
        mTitleBackIcon?.setTint(iconColor)
        iv_custom_back.setImageDrawable(mTitleBackIcon)
        setBackgroundColor(mTitleBackgroundColor)
    }

    private fun initUI(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.custom_title, this)
        tv_custom_title = findViewById(R.id.tv_custom_title)
        iv_custom_back = findViewById(R.id.iv_custom_back)
        tv_custom_cancel = findViewById(R.id.tv_custom_cancel)

        tv_custom_cancel.setOnClickListener {
            mOnTitleActionClickListener?.onMoreClick(it)
        }

        iv_custom_back.setOnClickListener {
            mOnTitleActionClickListener?.onBackClick()
        }
    }

    private fun getIconColor(color: Int): Int {
        if ((Color.red(color) * 0.299F + Color.green(color) * 0.578F + Color.blue(color) * 0.114F) < 192) {
            return Color.WHITE
        } else {
            return Color.BLACK
        }
    }

    fun setTitleBackgroundColor(color: Int) {
        mTitleBackgroundColor = color
        upDataIconColor()
    }

    fun setTitleBackIcon(drawable: Drawable) {
        mTitleBackIcon = drawable
        upDataIconColor()
    }

    private var mOnTitleActionClickListener: OnTitleActionClickListener? = null
    fun setOnTitleActionClickListener(onTitleActionClickListener: OnTitleActionClickListener) {
        this.mOnTitleActionClickListener = onTitleActionClickListener
    }

    interface OnTitleActionClickListener {
        fun onBackClick()
        fun onMoreClick(v: View)
    }
}