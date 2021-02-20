package com.nightrain.mediaselect.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nightrain.mediaselect.R
import com.nightrain.mediaselect.entity.MediaEntity
import com.nightrain.mediaselect.util.ImageLoadTool
import java.math.BigDecimal
import java.math.RoundingMode

class AudioAdapter(data: MutableList<MediaEntity>) :
    RecyclerView.Adapter<AudioAdapter.ViewHolder>() {
    private val mData = data

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mItemView = view
        val mName = mItemView.findViewById<TextView>(R.id.tv_item_name)
        val mSize = mItemView.findViewById<TextView>(R.id.tv_item_size)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_audio, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mName.text = mData[position].mediaName
        holder.mSize.text ="文件大小：".plus(initSize(mData[position].mediaSize)) .plus("M")
        holder.mItemView.setOnClickListener {
            mOnItemClickListener?.onItemClick(mData[position])
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    private var mOnItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(entity: MediaEntity)
    }

    private fun initSize(size: Long): String {
        val fSize = size / 1024F / 1024F
        return BigDecimal(fSize.toString()).setScale(2, RoundingMode.HALF_UP).toString()
    }
}