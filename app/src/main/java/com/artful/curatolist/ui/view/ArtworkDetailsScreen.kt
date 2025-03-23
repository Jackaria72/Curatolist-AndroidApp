package com.artful.curatolist.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.artful.curatolist.R
import com.artful.curatolist.model.Artwork
import com.artful.curatolist.viewmodel.ListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtworkDetails(navController: NavController, listViewModel: ListViewModel, snackbarHostState: SnackbarHostState) {

    val scrollState = rememberScrollState()
    val artwork = navController
        .currentBackStackEntry
        ?.arguments
        ?.getParcelable<Artwork>("artwork")

    // Handle the case where artwork is null
    if (artwork == null) {
        // Show an error message or navigate back
        Text("Artwork not found")
        return
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    listViewModel.addToFavourites(artwork)
                    CoroutineScope(Dispatchers.Main).launch {
                        snackbarHostState.showSnackbar("Artwork Added to Favourites")
                    }
                }
            ) {
                Icon(Icons.Default.Favorite, contentDescription = "Add to Favorites")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Respect root Scaffold padding
                .padding(16.dp)
        ) {


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

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = artwork.title,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Artist: ${artwork.artist}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Classification: ${artwork.classification}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Date: ${artwork.date}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Medium: ${artwork.medium}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Dimensions: ${artwork.dimensions}",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Cultural Origin: ${artwork.culturalOrigin}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewArtworkDetails() {
//    val sampleArtwork = Artwork(
//        id = "1_TST",
//        title = "Starry Night",
//        artist = "Vincent van Gogh",
//        date = "1890",
//        description = "test",
//        medium = "test",
//        technique = "test",
//        classification = "painting",
//        culturalOrigin = "test",
//        dimensions = "test",
//        imageUrl = "https://example.com/image.jpg",
//        source = "Harvard"
//    )
//    ArtworkDetails(artwork = sampleArtwork)
//}