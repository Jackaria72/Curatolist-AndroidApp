package com.artful.curatolist.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavDestination(val title: String, val route: String, val icon: ImageVector?) {
    object Home: NavDestination(title = "Home", route = "home_screen", icon = Icons.Filled.Home)
    object Search: NavDestination(title = "search", route = "search_screen/{query}", icon = Icons.Filled.Search) {
        fun createRoute(query: String) = "search_screen/$query"
    }
    object Lists: NavDestination(title = "My Lists", route = "lists_screen", icon = Icons.AutoMirrored.Filled.List)

    object Details: NavDestination(title = "Details", route = "details_screen", icon = null)

}