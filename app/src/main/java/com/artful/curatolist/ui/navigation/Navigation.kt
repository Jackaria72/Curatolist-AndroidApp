package com.artful.curatolist.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.artful.curatolist.model.Artwork
import com.artful.curatolist.room.data.ArtworkItem
import com.artful.curatolist.ui.view.ArtworkDetails
import com.artful.curatolist.ui.view.HomeScreen
import com.artful.curatolist.ui.view.AcknowledgeScreen
import com.artful.curatolist.ui.view.ExhibitArtDetails
import com.artful.curatolist.ui.view.ExhibitScreen
import com.artful.curatolist.ui.view.GalleryScreen
import com.artful.curatolist.ui.view.SearchScreen
import com.artful.curatolist.viewmodel.ArtworkViewModel
import com.artful.curatolist.viewmodel.ListViewModel

@Composable
fun AppNavigation(navController: NavHostController, paddingValues: PaddingValues, viewModel: ArtworkViewModel, listViewModel: ListViewModel, snackbarHostState: SnackbarHostState) {

    NavHost(
        navController = navController,
        startDestination = NavDestination.Home.route,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        //Home
        composable(NavDestination.Home.route) { HomeScreen(navController, viewModel) }
        //Search
        composable(
            route = NavDestination.Search.route,
            arguments = listOf(navArgument("query") { defaultValue = "" },
                navArgument("shouldTriggerSearch") { defaultValue = true })
        ) {
            backStackEntry ->
            val query = backStackEntry.arguments?.getString("query") ?: ""
            SearchScreen(
                viewModel = viewModel,
                query = query,
                navController = navController,
                navigateToDetails = { artwork ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("artwork", artwork)
                    navController.navigate(NavDestination.Details.route)
                }
            )
        }
        //Search Details
        composable(NavDestination.Details.route) {
            val artwork = navController.previousBackStackEntry?.savedStateHandle?.get<Artwork>("artwork")
            if (artwork != null) {
                ArtworkDetails(navController = navController, listViewModel = listViewModel, snackbarHostState = snackbarHostState)
            }
        }
        //Lists
        //All Lists
        composable(route = NavDestination.Lists.route) {
            GalleryScreen(
                listViewModel = listViewModel,
                navigateToListDetails = { listId ->
                    navController.navigate("list_details/$listId")
                },
                snackbarHostState = snackbarHostState) }
        //Individual List
        composable(
            route = "list_details/{listId}",
            arguments = listOf(navArgument("listId")  { type = NavType.LongType})
        ) {
            backstackEntry -> val listId = backstackEntry.arguments?.getLong("listId") ?: -1L
            ExhibitScreen(listId = listId, listViewModel = listViewModel,
                onNavigateToDetails = { artwork ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("artwork", artwork)
            navController.navigate("exhibit_art_details")
                }
            )
        }
        composable(route = "exhibit_art_details"
        ) {
            val artwork =
                navController.previousBackStackEntry?.savedStateHandle?.get<ArtworkItem>("artwork")
            if (artwork != null) {
                ExhibitArtDetails(artwork)
            }
        }
        //Acknowledgments
        composable(NavDestination.Acknowledgements.route) { AcknowledgeScreen() }
    }
}