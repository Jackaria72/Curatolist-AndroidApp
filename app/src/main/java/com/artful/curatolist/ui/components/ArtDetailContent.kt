package com.artful.curatolist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
fun ArtDetailContent(artwork: Artwork?) {
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(artwork?.imageUrl)
                .crossfade(true)
                .error(R.drawable.ic_no_image)
                .placeholder(R.drawable.ic_placeholder)
                .build(),
            contentDescription = "Artwork Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp)),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = artwork?.title ?: "Unknown Title",
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            DetailsTextFormat("Artist:", "${artwork?.artist}")

            Spacer(modifier = Modifier.height(8.dp))

            DetailsTextFormat("Description", "${artwork?.description}")

            Spacer(modifier = Modifier.height(8.dp))

            DetailsTextFormat("Classification:", "${artwork?.classification}")

            Spacer(modifier = Modifier.height(8.dp))

            DetailsTextFormat("Date:", "${artwork?.date}")

            Spacer(modifier = Modifier.height(8.dp))

            DetailsTextFormat("Medium:", "${artwork?.medium}")

            Spacer(modifier = Modifier.height(8.dp))

            DetailsTextFormat("Dimensions:", "${artwork?.dimensions}")

            Spacer(modifier = Modifier.height(8.dp))

            DetailsTextFormat("Cultural Origin:", "${artwork?.culturalOrigin}")
        }
    }
}
@Preview
@Composable
fun PreviewArtDetails(){
    val testArt = Artwork(
        id = "test",
        title = "Test Title",
        artist = "Test Artist",
        date = "Test Date",
        description = "Test Description",
        medium = "Test",
        technique = "test",
        classification = "Tests and Previews",
        culturalOrigin = "Testing",
        dimensions = "Hopefully the full screen",
        imageUrl = "test.com",
        source = "This Project"
    )
    ArtDetailContent(testArt)
}
@Composable
fun DetailsTextFormat(label: String, content: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label,
            style = MaterialTheme.typography.labelMedium)
        Text(text = content,
            style = MaterialTheme.typography.bodyLarge
            )
    }
}