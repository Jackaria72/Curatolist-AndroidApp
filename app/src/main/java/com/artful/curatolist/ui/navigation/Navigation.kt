package com.artful.curatolist.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.artful.curatolist.ui.view.HomeScreen
import com.artful.curatolist.ui.view.ListScreen
import com.artful.curatolist.ui.view.SearchScreen

@Composable
fun AppNavigation(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = NavDestination.Home.route,
        modifier = Modifier.fillMaxSize().padding(paddingValues)
    ) {
        composable(NavDestination.Home.route) { HomeScreen() }
        composable(NavDestination.Search.route) { SearchScreen() }
        composable(NavDestination.Lists.route) { ListScreen() }
    }
}