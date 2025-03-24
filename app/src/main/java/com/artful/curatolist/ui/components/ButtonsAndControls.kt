package com.artful.curatolist.ui.components

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PaginationControls(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    previousEnabled: Boolean,
    nextEnabled: Boolean

) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        ArrowButton(icon = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous Page", onClick = onPreviousClick, enabled = previousEnabled)
        ArrowButton(icon = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next Page", onClick = onNextClick, enabled = nextEnabled)
    }
}

@Composable
fun ArrowButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    IconButton(
        onClick = onClick,
        enabled = enabled,

        ) {
        Icon(imageVector = icon, contentDescription = contentDescription)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPaginationControls() {
    PaginationControls(
        onPreviousClick = { Log.d("PaginationControls", "Previous button clicked") },
        onNextClick = { Log.d("PaginationControls", "Next button clicked") },
        true,
        true
    )
}
@Composable
fun SettingsButton(
    onClick: () -> Unit,
    text: String,
    icon: ImageVector
) {
    Button(
        onClick = onClick,
    ) {
        Text(text)
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            icon,
            contentDescription = text
        )
    }
}