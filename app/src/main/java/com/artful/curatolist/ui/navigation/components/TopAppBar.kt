package com.artful.curatolist.ui.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {

    var expanded = remember { mutableStateOf(false) }
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    TopAppBar(
        title = { Text("Curatolist") },
        navigationIcon = {
            if (currentDestination == "details_screen" || currentDestination == "acknowledgement_screen") {
                IconButton(onClick = {
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "shouldTriggerSearch",
                        false
                    )
                    navController.popBackStack()
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            } else if (currentDestination?.startsWith("list_details") == true) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
        actions = {
            IconButton(onClick = { expanded.value = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More Options")
            }
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Acknowledgments") },
                    onClick = {
                        expanded.value = false
                        navController.navigate("acknowledgement_screen")
                    }
                )
            }
        }
    )
}