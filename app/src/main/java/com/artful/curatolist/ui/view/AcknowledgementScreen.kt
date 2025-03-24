package com.artful.curatolist.ui.view

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AcknowledgeScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.Center) {
            Text("Thank you!!!",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.displayLarge)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text("""
        The creator of Curatolist would like to thank everybody at both Harvard Art Museum and Chicago Institute of Art!
        Without their hard work and dedication to making these beautiful works of art easily accessible to the public!
        
        Please consider Visiting them and seeing all the great work they do!
    """.trimIndent(),
            style = MaterialTheme.typography.bodyLarge)
    }
}
@Preview
@Composable
fun PreviewAcknows(){
    AcknowledgeScreen()
}