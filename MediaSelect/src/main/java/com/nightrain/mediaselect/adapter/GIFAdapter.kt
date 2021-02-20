package com.nightrain.mediaselect.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nightrain.mediaselect.R
import com.nightrain.mediaselect.entity.MediaEntity
import com.nightrain.mediaselect.util.ImageLoadTool

class GIFAdapter(data: MutableList<MediaEntity>) :
    RecyclerView.Adapter<GIFAdapter.ViewHolder>() {
    private val mData = data

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mItemView = view
        val mImageView = mItemView.findViewById<ImageView>(R.id.iv_item_picture)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_gif, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        ImageLoadTool.loadImage(
            holder.mImageView.context,
            mData[position].mediaUri,
            holder.mImageView
        )


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
}