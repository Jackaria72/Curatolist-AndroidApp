package com.artful.curatolist.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artful.curatolist.ui.cards.ArtworkItem
import com.artful.curatolist.ui.components.SettingsButton
import com.artful.curatolist.viewmodel.ListViewModel
import com.artful.curatolist.viewmodel.util.toArtwork

@Composable
fun ListDetailsScreen(
    listId: Long,
    listViewModel: ListViewModel
) {
    var isEditMode by remember { mutableStateOf(false) }

    LaunchedEffect(listId) {
        listViewModel.getListWithItems(listId)
    }

    val listDetailsState = listViewModel.listDetailsState


    Column {
        Text(text = listDetailsState.list.list.listName)
        LazyColumn {
            item {
                Row(horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .padding(5.dp)) {
                    Box(modifier = Modifier.weight(1f))
                    SettingsButton(
                        onClick = { isEditMode = !isEditMode  },
                        text = "Edit Exhibit",
                        icon = Icons.Filled.Menu
                    )
                }
            }
            items(listDetailsState.list.items) { item ->
                val art = item.toArtwork()
                ArtworkItem(
                    artwork = art,
                    onClick = { },
                    isEditMode = isEditMode,
                    onDelete = { listViewModel.removeArt(item) }
                )
            }
        }
    }
}