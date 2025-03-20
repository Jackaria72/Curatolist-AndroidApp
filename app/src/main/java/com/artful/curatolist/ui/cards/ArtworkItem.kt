package com.artful.curatolist.ui.cards


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.error
import coil3.request.placeholder
import com.artful.curatolist.R
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(artwork.imageUrl)
                    .error(R.drawable.ic_error)
                    .placeholder(R.drawable.ic_placeholder)
                    .build(),
                contentDescription = "Artwork Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )
            Spacer(modifier = Modifier
                .width(16.dp))
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = artwork.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = artwork.artist,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = artwork.classification,
                    style = MaterialTheme.typography.bodySmall
                )
            }
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
        imageUrl = "https://picsum.photos/200/300",
        source = "Harvard"
    )
    ArtworkItem(sampleArtwork, {  })

}