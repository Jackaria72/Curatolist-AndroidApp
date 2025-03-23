package com.artful.curatolist.ui.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.artful.curatolist.viewmodel.ListViewModel
import com.artful.curatolist.ui.cards.ListItem

@Composable
fun ListScreen(
    listViewModel: ListViewModel,
    navigateToListDetails: (Long) -> Unit
) {
    val listState = listViewModel.state

    LazyColumn {
        items(listState.items) { item ->
            ListItem(
                list = item,
                onClick = { navigateToListDetails(item.listId) }
            )
        }
    }
}