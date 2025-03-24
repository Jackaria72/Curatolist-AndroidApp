package com.artful.curatolist.ui

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.artful.curatolist.network.RetrofitInstance
import com.artful.curatolist.repository.CuratolistRepository
import com.artful.curatolist.room.Graph
import com.artful.curatolist.room.dao.ArtworkListDao
import com.artful.curatolist.room.database.CuratolistDatabase
import com.artful.curatolist.ui.navigation.AppNavigation
import com.artful.curatolist.ui.navigation.components.AppBottomNavigation
import com.artful.curatolist.ui.navigation.components.TopBar
import com.artful.curatolist.viewmodel.ArtworkViewModel
import com.artful.curatolist.viewmodel.ArtworkViewModelFactory
import com.artful.curatolist.viewmodel.ListViewModel
import com.artful.curatolist.viewmodel.ListViewModelFactory

@Composable
fun AppRoot() {


    val snackbarHostState = remember { SnackbarHostState() }

    val apiService = RetrofitInstance.api

    val repository = remember { CuratolistRepository(apiService) }
    val roomRepository = remember { Graph.repository }

    val viewModel: ArtworkViewModel = viewModel(
        factory = ArtworkViewModelFactory(repository)
    )
    val listViewModel : ListViewModel = viewModel(
        factory = ListViewModelFactory(roomRepository)
    )
    val navController = rememberNavController()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)},
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(navController) },
        bottomBar = { AppBottomNavigation(navController) }
    ) { paddingValues ->
        AppNavigation(navController, paddingValues, viewModel, listViewModel, snackbarHostState) }

}