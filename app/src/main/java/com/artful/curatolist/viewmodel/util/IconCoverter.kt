package com.artful.curatolist.viewmodel.util

import com.artful.curatolist.R

fun getIconResId(icon: String): Int {
    return when (icon) {
        "canvas" -> R.drawable.ic_canvas
        "frame" -> R.drawable.ic_frame_logo
        else -> R.drawable.ic_placeholder
    }
}