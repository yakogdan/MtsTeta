package com.yakogdan.mtsteta.presentation.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MovieGenresItemDecoration(
    private val startFirst: Int,
    private val endAll: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = this@MovieGenresItemDecoration.startFirst
        }
        outRect.right = this@MovieGenresItemDecoration.endAll
    }
}