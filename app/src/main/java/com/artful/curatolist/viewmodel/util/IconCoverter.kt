package com.artful.curatolist.viewmodel.util

import com.artful.curatolist.R

fun getIconResId(icon: String): Int {
    return when (icon) {
        "star" -> R.drawable.ic_placeholder
        "heart" -> R.drawable.ic_launcher_foreground
        else -> R.drawable.ic_error
    }
}