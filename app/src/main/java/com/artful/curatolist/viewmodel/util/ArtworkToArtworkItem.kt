package com.artful.curatolist.viewmodel.util

import com.artful.curatolist.model.Artwork
import com.artful.curatolist.room.data.ArtworkItem

fun Artwork.toArtworkItem(listId: Long): ArtworkItem {
    return ArtworkItem(
        artworkId = 0, // Room will auto-generate this ID
        title = this.title,
        artist = this.artist,
        date = this.date,
        description = this.description,
        medium = this.medium,
        technique = this.technique,
        classification = this.classification,
        culturalOrigin = this.culturalOrigin,
        dimensions = this.dimensions,
        imageUrl = this.imageUrl,
        source = this.source,
        listId = listId
    )
}