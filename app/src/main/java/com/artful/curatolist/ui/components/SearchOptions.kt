package com.artful.curatolist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artful.curatolist.model.FilterType
import java.nio.file.WatchEvent

@Composable
fun SearchOptions(
    selectedSource: String,
    selectedFilter: String,
    selectedFilterValue: String,
    selectedSort: String,
    onSourceSelected: (String) -> Unit,
    onFilterSelected: (String) -> Unit,
    onFilterValueSelected: (String) -> Unit,
    onSortSelected: (String) -> Unit,
    filterValues: List<String>
) {
    val museumSources = listOf<String>("All", "Harvard", "Chicago")
    val sortOptions = listOf<String>("", "Default", "Name", "Classification")
    val filterOptions = FilterType.filterTypes.map { it.displayName }

    Row {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)

        ) {
            OptionDropdown(
                label = "Museum Source:",
                items = museumSources,
                selectedItem = selectedSource,
                onItemSelected = onSourceSelected
            )

            Spacer(modifier = Modifier.height(8.dp))

            OptionDropdown(
                label = "Filter Type:",
                items = filterOptions,
                selectedItem = selectedFilter,
                onItemSelected = onFilterSelected
            )

            Spacer(modifier = Modifier.height(8.dp))

            OptionDropdown(
                label = "Filter Value:",
                items = filterValues,
                selectedItem = selectedFilterValue,
                onItemSelected = onFilterValueSelected,
            )

            Spacer(modifier = Modifier.height(8.dp))

            OptionDropdown(
                label = "Sort Results By:",
                items = sortOptions,
                selectedItem = selectedSort,
                onItemSelected = onSortSelected
            )
        }
    }
}
@Composable
fun Filters() {

}

@Preview
@Composable
fun PreviewSearchFilters(){
//    SearchFilters()
}