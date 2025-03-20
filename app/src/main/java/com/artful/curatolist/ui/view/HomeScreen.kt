package com.artful.curatolist.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.artful.curatolist.ui.components.AppSearchBar
import com.artful.curatolist.ui.navigation.NavDestination
import com.artful.curatolist.viewmodel.ArtworkViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: ArtworkViewModel) {
    var query by viewModel.q

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppSearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearch = { navController.navigate(NavDestination.Search.createRoute(query)) },
            false,
            {  }
        )
    }
}