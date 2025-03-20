package com.artful.curatolist.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.artful.curatolist.R
import com.artful.curatolist.model.Artwork

@Composable
fun ArtworkDetails(artwork: Artwork?) {

    val scrollState = rememberScrollState()

    if(artwork == null) {
        Text(text = "Unable To Fetch Artwork Details")
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(artwork.imageUrl)
                    .crossfade(true)
                    .error(R.drawable.ic_error)
                    .placeholder(R.drawable.ic_placeholder)
                    .build(),
                contentDescription = "Artwork Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp)),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = artwork.title)
                Text(text = artwork.artist)
                Text(text = artwork.classification)
            }
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