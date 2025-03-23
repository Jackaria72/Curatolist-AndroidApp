package com.artful.curatolist.room.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ArtworkListWithArt(
    @Embedded
    val list: ArtworkList,
    @Relation(
        parentColumn = "listId",
        entityColumn = "artworkId",
        associateBy = Junction(ArtListArtWorkCrossRef::class)
    ) val items: List<ArtworkItem>
) {
    constructor() : this(ArtworkList(0, "", "", false), emptyList())
}
