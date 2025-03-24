package com.artful.curatolist.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artful.curatolist.R
import com.artful.curatolist.ui.components.AppSearchBar
import com.artful.curatolist.ui.navigation.NavDestination
import com.artful.curatolist.viewmodel.ArtworkViewModel
@Composable
fun HomeScreen(navController: NavController, viewModel: ArtworkViewModel) {
    var query by viewModel.q

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppSearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearch = { navController.navigate(NavDestination.Search.createRoute(query)) },
            false,
            {  }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = "Curatolist Banner"
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Text(
                text = """Hello and welcome to Curatolist! You're very own Art Gallery in your pocket!
            |Browse Artworks from multiple museums, from the comfort of your own home or on the go! 
            |Add them to your very own Exhibits in your own personal Gallery!
        """.trimMargin(),
                style = MaterialTheme.typography.bodyLarge
            )

        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Go  forth and Curatolist!")
    }
}
