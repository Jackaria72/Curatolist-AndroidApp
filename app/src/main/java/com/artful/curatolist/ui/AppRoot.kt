package com.artful.curatolist.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.artful.curatolist.network.RetrofitInstance
import com.artful.curatolist.repository.CuratolistRepository
import com.artful.curatolist.ui.navigation.AppNavigation
import com.artful.curatolist.ui.navigation.components.AppBottomNavigation
import com.artful.curatolist.ui.navigation.components.TopBar
import com.artful.curatolist.viewmodel.ArtworkViewModel
import com.artful.curatolist.viewmodel.ArtworkViewModelFactory

@Composable
fun AppRoot() {
    val apiService = RetrofitInstance.api
    val repository = CuratolistRepository(apiService)
    val viewModel: ArtworkViewModel = viewModel(
        factory = ArtworkViewModelFactory(repository)
    )
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(navController) },
        bottomBar = { AppBottomNavigation(navController) }
    ) { paddingValues ->
        AppNavigation(navController, paddingValues, viewModel) }
}