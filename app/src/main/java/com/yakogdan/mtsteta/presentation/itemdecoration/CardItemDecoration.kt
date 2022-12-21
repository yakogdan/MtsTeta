package com.yakogdan.mtsteta.presentation.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CardItemDecoration(
    private val start: Int,
    private val end: Int,
    private var bottom: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.apply {
            outRect.left = this@CardItemDecoration.start
            outRect.right = this@CardItemDecoration.end
            outRect.bottom = this@CardItemDecoration.bottom
        }
    }
}