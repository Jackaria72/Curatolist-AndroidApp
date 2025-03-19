package com.artful.curatolist.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.artful.curatolist.model.Artwork
import com.artful.curatolist.ui.cards.ArtworkItem
import com.artful.curatolist.viewmodel.ArtworkViewModel


@Composable
fun SearchScreen(viewModel: ArtworkViewModel = viewModel()) {
    val artwork = viewModel.art.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (artwork.isEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize()
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(items = artwork) {
                    item -> ArtworkItem(item)
                }
                }
            }
        }
    }
