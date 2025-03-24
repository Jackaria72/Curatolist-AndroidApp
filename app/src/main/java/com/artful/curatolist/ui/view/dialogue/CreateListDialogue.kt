package com.artful.curatolist.ui.view.dialogue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.artful.curatolist.viewmodel.util.getIconResId

@Composable
fun CreateListDialog(
    onDismiss: () -> Unit,
    onCreate: (String, String) -> Unit
) {
    var listName by remember { mutableStateOf("") }
    var selectedIcon by remember { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) }

    val icons = listOf(
        "star" to "Star",
        "heart" to "Heart")

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.padding(5.dp)) {
                    Text("Create New Exhibit")
                }
                OutlinedTextField(
                    value = listName,
                    onValueChange = { listName = it },
                    label = { Text("Exhibit Name") }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                    OutlinedButton(
                        onClick = { expanded = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if(selectedIcon != null) {
                            Icon(
                                painter = painterResource(id = getIconResId(selectedIcon!!)),
                                contentDescription = "Selected Icon"
                            )
                        } else {
                            Text("Choose an Icon")
                        }
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        icons.forEach { (icon, label) ->
                            DropdownMenuItem(
                                text = { Text(label) },
                                onClick = {
                                    selectedIcon = icon
                                    expanded = false
                                },
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(id = getIconResId(icon)),
                                        contentDescription = label
                                    )
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }

                    TextButton(
                        onClick = {
                            if (listName.isNotEmpty()) {
                                onCreate(listName, selectedIcon!!)
                                onDismiss()
                            }
                        }
                    ) {
                        Text("Create!")
                    }
                }
            }
        }
    }
}
//@Preview
//@Composable
//fun PreviewDialogue() {
//    CreateListDialog(
//        onDismiss = { },
//        onCreate = { ("Hi", "Art") -> Unit  }
//    )
//}