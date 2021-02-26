package com.example.test.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemSpacingDecoration : RecyclerView.ItemDecoration() {

    companion object {
        private const val SAME_ITEM_SPACE = 20
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val side = 20

        val leading = SAME_ITEM_SPACE
        outRect.run {
            top = leading
            bottom = 0
            left = side
            right = side
        }
    }
}