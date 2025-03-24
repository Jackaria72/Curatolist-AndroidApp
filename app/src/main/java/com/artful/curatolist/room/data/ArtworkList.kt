package com.artful.curatolist.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "artwork_lists")
data class ArtworkList(
    @PrimaryKey(autoGenerate = true)
    val listId: Long = 0,
    val listName: String,
    val icon: String,
    val isDefault: Boolean = false
)
