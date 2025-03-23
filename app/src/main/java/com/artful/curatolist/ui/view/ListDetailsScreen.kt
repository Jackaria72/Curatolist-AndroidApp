package com.artful.curatolist.ui.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.artful.curatolist.room.data.ArtworkItem
import com.artful.curatolist.viewmodel.ListViewModel

@Composable
fun ListDetailsScreen(
    listId: Long,
    listViewModel: ListViewModel
){
    LaunchedEffect(listId) {
        listViewModel.getListWithItems(listId)
    }

    val listDetailsState = listViewModel.listDetailsState

    LazyColumn {
        item{ Text(text = listDetailsState.list.list.listName)}
    }






}