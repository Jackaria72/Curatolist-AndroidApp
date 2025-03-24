package com.artful.curatolist.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.artful.curatolist.room.data.ArtworkItem
import com.artful.curatolist.ui.components.ArtDetailContent
import com.artful.curatolist.viewmodel.util.toArtwork

@Composable
fun ExhibitArtDetails(artwork: ArtworkItem) {
//    val artwork = navController
//        .previousBackStackEntry
//        ?.savedStateHandle
//        ?.get<ArtworkItem>("artwork")


    Column(modifier = Modifier.fillMaxSize()) {
        if (artwork?.toArtwork() == null) {
            Text("No Artwork Found")
        } else {
            ArtDetailContent(artwork.toArtwork())
        }

    }
}