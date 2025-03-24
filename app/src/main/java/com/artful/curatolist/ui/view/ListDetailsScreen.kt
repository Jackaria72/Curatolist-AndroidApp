package com.artful.curatolist.ui.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.artful.curatolist.ui.cards.ArtworkItem
import com.artful.curatolist.viewmodel.ListViewModel
import com.artful.curatolist.viewmodel.util.toArtwork

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
        items(listDetailsState.list.items) { item ->
            val art = item.toArtwork()
            ArtworkItem(
                artwork = art,
                onClick = {  }
            )
        }
    }
}