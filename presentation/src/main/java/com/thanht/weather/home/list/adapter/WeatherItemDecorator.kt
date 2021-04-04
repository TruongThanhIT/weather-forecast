package com.thanht.weather.home.list.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.thanht.weather.R

class WeatherItemDecorator(context: Context) : RecyclerView.ItemDecoration() {
    private val divider: Drawable? = ContextCompat.getDrawable(context, R.drawable.line_divider_1dp)
    private val spacing: Int = context.resources.getDimensionPixelSize(R.dimen.divider_height)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        divider ?: return
        for (pos in 1 until parent.childCount) {
            val child = parent.getChildAt(pos)
            val position = parent.getChildAdapterPosition(child)
            if (position != RecyclerView.NO_POSITION) {
                divider.apply {
                    setBounds(0, child.top - divider.intrinsicHeight, parent.width, child.top)
                    draw(c)
                }
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val pos = parent.getChildAdapterPosition(view)
        if (pos != 0 && pos != RecyclerView.NO_POSITION) {
            outRect.top = spacing
        }
    }
}