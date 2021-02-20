package com.nightrain.mediaselect.custom

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomGridLayoutItemDecoration(divWidth: Int) : RecyclerView.ItemDecoration() {
    private val mDivWidth = divWidth

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val mLayoutManager = parent.layoutManager
        if (mLayoutManager is GridLayoutManager) {
            val itemPosition = parent.getChildAdapterPosition(view)
            val mSpanCount = mLayoutManager.spanCount
            var mChildCount = 0
            parent.adapter?.let {
                mChildCount = it.itemCount
            }
            val isLastRow = isLastRow(itemPosition, mSpanCount, mChildCount)

            val top = 0
            var left = 0
            var right = 0
            var bottom = 0
            //每个Item需要分担的宽度
            val eachWidth = (mSpanCount - 1) * mDivWidth / mSpanCount
            val dl = mDivWidth - eachWidth;
            left = itemPosition % mSpanCount * dl
            right = eachWidth - left;
            bottom = mDivWidth;
            if (isLastRow) {
                bottom = 0;
            }
            outRect.set(left, top, right, bottom)
        }
    }

    private fun isLastRow(pos: Int, spanCount: Int, childCount: Int): Boolean {
        //最后一行
        val lines =
            if (childCount % spanCount == 0) childCount / spanCount else childCount / spanCount + 1
        //当前Position是否在最后一行
        return lines == pos / spanCount + 1;
    }

}