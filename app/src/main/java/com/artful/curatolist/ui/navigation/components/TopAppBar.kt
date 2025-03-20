package com.artful.curatolist.ui.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {

    val currentDestination =navController.currentBackStackEntryAsState().value?.destination?.route

    TopAppBar(
        title = {Text("Curatolist") },
        navigationIcon = {
            if (currentDestination == "details_screen") {
                IconButton(onClick = {
                    navController.previousBackStackEntry?.savedStateHandle?.set("shouldTriggerSearch",false)
                    navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        }
    )
}