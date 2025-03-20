package com.artful.curatolist.ui.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.artful.curatolist.ui.navigation.NavDestination

@Composable
fun AppBottomNavigation(navController: NavController) {
    val items = listOf(
        NavDestination.Home,
        NavDestination.Search,
        NavDestination.Lists
    )

    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState().value
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon ?: Icons.Default.Warning, contentDescription = null) },
                label = { Text(screen.title) },
                selected = currentDestination?.route == screen.route,
                onClick = {navController.navigate(screen.route)}
            )
        }
    }
}