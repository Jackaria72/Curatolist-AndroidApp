package com.artful.curatolist.ui.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.artful.curatolist.model.Artwork
import com.artful.curatolist.model.FilterType
import com.artful.curatolist.model.FilterType.Companion.filterTypes
import com.artful.curatolist.model.SearchQueries

import com.artful.curatolist.ui.cards.ArtworkItem
import com.artful.curatolist.ui.components.AppSearchBar
import com.artful.curatolist.ui.components.PaginationControls
import com.artful.curatolist.ui.components.SearchOptions
import com.artful.curatolist.viewmodel.ArtworkViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: ArtworkViewModel,
                 query: String,
                 navController: NavController,
                 navigateToDetails: (Artwork) -> Unit) {

    val allArtwork = viewModel.art.value
    val artwork = viewModel.paginatedArtwork.collectAsStateWithLifecycle().value
    val currentAppPage = viewModel.currentAppPage.value
    val isLoading = viewModel.isLoading.value
    val currentApiPage = viewModel.currentApiPage.value
    val pageInfo = viewModel.pageInfo.value

    var query by viewModel.q
    val selectedSource = remember { mutableStateOf("Select Source") }
    val selectedFilter = remember { mutableStateOf<FilterType?>(FilterType.filterTypes[0]) }
    val selectedFilterValue = remember { mutableStateOf("Select Value") }
    val selectedSort = remember { mutableStateOf("Select Sort") }
    var isSearchBarActive by remember { mutableStateOf(false) }

    val searchQueries = SearchQueries(
        search = query,
        source = selectedSource.value,
        filterType = selectedFilter.value?.displayName ?: "",
        filterValue = selectedFilterValue.value,
        sort = selectedSort.value
    )
    val filterValues =
        if (selectedFilter.value == FilterType.Classification) allArtwork.map { it.classification }.distinct()
        else if (selectedFilter.value == FilterType.Technique) allArtwork.map { it.technique }.distinct()
        else if (selectedFilter.value == FilterType.Medium) allArtwork.map { it.medium }.distinct()
        else emptyList()

    val filterOptions = FilterType.filterTypes.map { it.displayName }

    val shouldTriggerSearch = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.get<Boolean>("shouldTriggerSearch") != false

    LaunchedEffect(shouldTriggerSearch) {
        if (shouldTriggerSearch) {
            viewModel.onQueryChange(query)
            viewModel.getArtList(1, searchQueries)
        }
    }

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
            onSearch = { viewModel.getArtList(1, searchQueries) },
            isActive = isSearchBarActive,
            { isSearchBarActive = it },
            content = {
                SearchOptions(
                    selectedSource = selectedSource.value,
                    selectedFilter = selectedFilter.value?.displayName ?: "Select Filter Type",
                    selectedFilterValue = selectedFilterValue.value,
                    selectedSort = selectedSort.value,
                    onSourceSelected = { selectedSource.value = it },
                    onFilterSelected = {
                        displayName -> selectedFilter.value = FilterType.filterTypes.first { it.displayName == displayName }
                        selectedFilterValue.value = "Filter By" },
                    onFilterValueSelected = { selectedFilterValue.value = it },
                    onSortSelected = { selectedSort.value = it },
                    filterValues = filterValues
                )
            }
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
                    items(items = artwork) { singleArt ->
                        ArtworkItem(
                            artwork = singleArt,
                            onClick = { navigateToDetails(singleArt) },
                            isEditMode = false,
                            onDelete = {  }
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PaginationControls(
                onPreviousClick = { viewModel.previousPage(searchQueries) },
                onNextClick = { viewModel.nextPage(searchQueries) },
                previousEnabled = currentAppPage > 0 || currentApiPage > 1,
                nextEnabled = ((currentAppPage+1)*20 < allArtwork.size || currentApiPage < (pageInfo?.combinedPageTotal ?: 0))
            )
        }
        }
    }
