package com.artful.curatolist.ui.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artful.curatolist.model.Artwork

@Composable
fun ArtworkItem(artwork: Artwork, onClick: () -> Unit) {
    Card(
    modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onClick() },
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    shape = RoundedCornerShape(8.dp)
    ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxHeight()
            ) {
                Text(text = artwork.title)
                Text(text = artwork.artist)
                Text(text = artwork.classification)
            }


    }
}
@Preview(showBackground = true)
@Composable
fun PreviewArtworkItem() {
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
        imageUrl = "test",
        source = "Harvard"
    )

}