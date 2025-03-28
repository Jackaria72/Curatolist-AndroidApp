package com.artful.curatolist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artwork(
    val id : String,
    val title : String,
    val artist : String,
    val date : String,
    val description : String,
    val medium : String,
    val technique : String,
    val classification : String,
    val culturalOrigin : String,
    val dimensions : String,
    val imageUrl : String,
    val source : String
) : Parcelable
