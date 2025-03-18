package com.artful.curatolist.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.artful.curatolist.ui.navigation.AppNavigation
import com.artful.curatolist.ui.navigation.components.AppBottomNavigation
import com.artful.curatolist.ui.navigation.components.TopBar

@Composable
fun AppRoot() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(navController) },
        bottomBar = { AppBottomNavigation(navController) }
    ) { paddingValues ->
        AppNavigation(navController, paddingValues) }
}