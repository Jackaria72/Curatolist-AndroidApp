package com.artful.curatolist.model

data class Artwork(
    val id : String,
    val title : String,
    val artist : String,
    val date : String,
    val period : String,
    val medium : String,
    val technique : String,
    val classification : String,
    val culturalOrigin : String,
    val dimensions : String,
    val imageUrl : String,
    val source : String
)
