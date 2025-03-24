package com.artful.curatolist.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artful.curatolist.viewmodel.ListViewModel
import com.artful.curatolist.ui.cards.ListItem
import com.artful.curatolist.ui.view.dialogue.CreateListDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ListScreen(
    listViewModel: ListViewModel,
    navigateToListDetails: (Long) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    val listState = listViewModel.state
    var showDialog by remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Create List")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
        ) {

            LazyColumn {
                items(listState.items) { item ->
                    ListItem(
                        list = item,
                        onClick = { navigateToListDetails(item.listId) }
                    )
                }
            }
        }
        if (showDialog) {
            CreateListDialog(
                onDismiss = { showDialog = false },
                onCreate = { name, icon ->
                    listViewModel.addList(name, icon)
                    showDialog = false
                    CoroutineScope(Dispatchers.Main).launch {
                        snackbarHostState.showSnackbar("Exhibit: $name Created!")
                    }
                }
            )
        }
    }
}