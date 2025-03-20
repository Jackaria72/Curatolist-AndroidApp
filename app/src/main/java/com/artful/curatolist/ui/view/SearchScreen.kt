package com.artful.curatolist.ui.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.artful.curatolist.model.Artwork
import com.artful.curatolist.ui.cards.ArtworkItem
import com.artful.curatolist.ui.components.AppSearchBar
import com.artful.curatolist.ui.components.PaginationControls
import com.artful.curatolist.viewmodel.ArtworkViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: ArtworkViewModel) {

    val allArtwork = viewModel.art.value
    val artwork = viewModel.paginatedArtwork.collectAsStateWithLifecycle().value
    val currentAppPage = viewModel.currentAppPage.value
    val isLoading = viewModel.isLoading.value
    val currentApiPage = viewModel.currentApiPage.value
    val pageInfo = viewModel.pageInfo.value

    var query by viewModel.q
    var isSearchBarActive by remember { mutableStateOf(false) }

    BackHandler(enabled = isSearchBarActive) {
        isSearchBarActive = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        AppSearchBar(
            query = query,
            onQueryChange = { viewModel.onQueryChange(it) },
            onSearch = { viewModel.getArtList(1, query) },
            isActive = isSearchBarActive,
            { isSearchBarActive = it } ,
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(top = 4.dp)
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(100.dp)
                        .align(alignment = Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(items = artwork) { item: Artwork ->
                        ArtworkItem(item)
                    }
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
