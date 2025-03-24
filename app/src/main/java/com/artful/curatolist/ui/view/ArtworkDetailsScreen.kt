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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.artful.curatolist.ui.cards.ListItem
import com.artful.curatolist.ui.components.ArtDetailContent
import com.artful.curatolist.viewmodel.ListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtworkDetails(navController: NavController, listViewModel: ListViewModel, snackbarHostState: SnackbarHostState) {

    val scrollState = rememberScrollState()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val artwork = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<Artwork>("artwork")
    val listState = listViewModel.state

    if (showBottomSheet) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { showBottomSheet = false },

    ) {
        Column {
            listState.items.forEach { list ->
                ListItem(
                    list = list,
                    onClick = {
                        listViewModel.addArtToList(artwork!!, list.listId)
                        showBottomSheet = false
                        CoroutineScope(Dispatchers.Main).launch {
                            snackbarHostState.showSnackbar("Artwork added to: ${list.listName}")
                        }
                    }
                )
            }
        }
    }

    }
        if (artwork == null) {
        Text("Artwork not found")
        }

        Scaffold(
            floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    listViewModel.addArtToList(artwork!!, 1L)
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
                    .verticalScroll(scrollState)
                    .padding(paddingValues)
                    .padding(16.dp),
        ) {
            ArtDetailContent(artwork)


            Spacer(modifier = Modifier.height(50.dp))

            Button(
                onClick = { showBottomSheet = true },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Add to your Gallery")
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