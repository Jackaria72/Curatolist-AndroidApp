package com.artful.curatolist.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    isActive: Boolean,
    onActiveChange: (Boolean) -> Unit,
    content: @Composable (() -> Unit)? = null
){
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = {
                    onSearch()
                    onActiveChange(false)
                           },
                expanded = isActive,
                onExpandedChange = { newActiveState -> onActiveChange(newActiveState) },
                enabled = true,
                placeholder = { if (query.isEmpty()) Text("Search Artworks") },
                leadingIcon = {
                    if (isActive) {
                        IconButton(onClick = { onActiveChange(false) }) {
                            Icon(Icons.Filled.Close, contentDescription = "Close")
                        }
                    } else {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { onQueryChange("") }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear")
                        }
                    }
                },

                )
        },
        expanded = isActive,
        onExpandedChange = { onActiveChange },
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        content = { content?.invoke() },
    )
}