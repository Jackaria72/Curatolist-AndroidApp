package com.artful.curatolist.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.artful.curatolist.model.Artwork
import com.artful.curatolist.ui.cards.ArtworkItem
import com.artful.curatolist.ui.components.PaginationControls
import com.artful.curatolist.viewmodel.ArtworkViewModel


@Composable
fun SearchScreen(viewModel: ArtworkViewModel) {
    val allArtwork = viewModel.art.value
    val artwork = viewModel.paginatedArtwork.collectAsStateWithLifecycle().value
    val currentAppPage = viewModel.currentAppPage.value
    val isLoading = viewModel.isLoading.value
    val currentApiPage = viewModel.currentApiPage.value
    val pageInfo = viewModel.pageInfo.value


    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(100.dp)
                    .align(alignment = Alignment.CenterHorizontally),
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(items = artwork) {
                    item: Artwork -> ArtworkItem(item)
                }
                }
            }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PaginationControls(
                onPreviousClick = { viewModel.previousPage() },
                onNextClick = { viewModel.nextPage() },
                previousEnabled = currentAppPage > 0 || currentApiPage > 1,
                nextEnabled = ((currentAppPage+1)*20 < allArtwork.size || currentApiPage < (pageInfo?.combinedPageTotal ?: 0))
            )
        }
        }
    }
