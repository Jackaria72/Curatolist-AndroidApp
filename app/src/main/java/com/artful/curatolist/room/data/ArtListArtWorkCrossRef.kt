package com.artful.curatolist.room.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index

@Entity(
    primaryKeys = ["listId", "artworkId"],
    foreignKeys = [
        ForeignKey(entity = ArtworkList::class, parentColumns = ["listId"], childColumns = ["listId"], onDelete = CASCADE),
        ForeignKey(entity = ArtworkItem::class, parentColumns = ["artworkId"], childColumns = ["artworkId"], onDelete = CASCADE)
    ],
    indices = [
        Index(value = ["artworkId"])]
)
data class ArtListArtWorkCrossRef(
    val listId: Long,
    val artworkId: String
)
