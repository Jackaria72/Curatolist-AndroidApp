package com.artful.curatolist.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.artful.curatolist.model.Artwork
import com.artful.curatolist.ui.view.ArtworkDetails
import com.artful.curatolist.ui.view.HomeScreen
import com.artful.curatolist.ui.view.ListScreen
import com.artful.curatolist.ui.view.SearchScreen
import com.artful.curatolist.viewmodel.ArtworkViewModel
import com.google.gson.Gson

@Composable
fun AppNavigation(navController: NavHostController, paddingValues: PaddingValues, viewModel: ArtworkViewModel) {
    NavHost(
        navController = navController,
        startDestination = NavDestination.Home.route,
        modifier = Modifier.fillMaxSize().padding(paddingValues)
    ) {
        composable(NavDestination.Home.route) { HomeScreen(navController, viewModel) }
        composable(
            route = NavDestination.Search.route,
            arguments = listOf(navArgument("query") { defaultValue = "" })
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
        composable(route = NavDestination.Lists.route) { ListScreen() }

        composable(NavDestination.Details.route) {
            val artwork =
                navController.previousBackStackEntry?.savedStateHandle?.get<Artwork>("artwork")
            if (artwork != null) {
                ArtworkDetails(artwork = artwork)
            }
        }
    }
}