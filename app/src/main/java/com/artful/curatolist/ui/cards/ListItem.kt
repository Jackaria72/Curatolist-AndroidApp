package com.artful.curatolist.ui.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artful.curatolist.room.data.ArtworkList

@Composable
fun ListItem(list: ArtworkList,
             onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (list.icon == "heart") {
                    Icons.Outlined.Favorite
                } else {
                    Icons.AutoMirrored.Outlined.List
                },
                contentDescription = "List Icon: ${list.icon}",
            )
            Spacer(modifier = Modifier
                .weight(1F))
            Text(text = list.listName)
        }

    }
}
@Preview
@Composable
fun PreviewListItem(){
    val testList = ArtworkList(
        listId = 1L,
        listName = "Favourites",
        icon = "heart",
        isDefault = true
    )

    ListItem(testList, { })
}