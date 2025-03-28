package com.artful.curatolist.room.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "artwork_items")
data class ArtworkItem(
    @PrimaryKey(autoGenerate = false)
    val artworkId : String,
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
    val source : String,
    val listId: Long
) : Parcelable
