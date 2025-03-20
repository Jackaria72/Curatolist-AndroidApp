package com.artful.curatolist.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artful.curatolist.model.Artwork

@Composable
fun ArtworkDetails(artwork: Artwork?) {
    if(artwork == null) {
        Text(text = "Unable To Fetch Artwork Details")
    } else {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),

            ) {

            item { Text(text = artwork.title) }
            item { Text(text = artwork.artist) }
            item { Text(text = artwork.classification) }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewArtworkDetails() {
    val sampleArtwork = Artwork(
        id = "1_TST",
        title = "Starry Night",
        artist = "Vincent van Gogh",
        date = "1890",
        period = "test",
        medium = "test",
        technique = "test",
        classification = "painting",
        culturalOrigin = "test",
        dimensions = "test",
        imageUrl = "https://example.com/image.jpg",
        source = "Harvard"
    )
    ArtworkDetails(artwork = sampleArtwork)
}