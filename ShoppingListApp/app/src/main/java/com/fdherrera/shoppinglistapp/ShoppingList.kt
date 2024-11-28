package com.fdherrera.shoppinglistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ShoppingItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false,
    var address: String = ""
)

@Composable
fun ShoppingListApp() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        val shoppingItems = remember { mutableStateOf(listOf<ShoppingItem>()) }
        val showDialog = remember { mutableStateOf(false) }

        Button(
            onClick = { showDialog.value = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Add Item")
        }
        ShoppingItemsView(shoppingItems)
        if (showDialog.value) {
            AddShoppingItemView(showDialog, shoppingItems)
        }
    }
}

@Composable
fun ShoppingItemsView(shoppingItems: MutableState<List<ShoppingItem>>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(shoppingItems.value) { shoppingItem ->
            if (shoppingItem.isEditing) {
                ShoppingItemEditor(
                    item = shoppingItem,
                    onEditComplete = { newName, newQty ->
                        shoppingItems.value = shoppingItems.value.map { it.copy(isEditing = false) }
                        shoppingItems.value
                            .find { it.id == shoppingItem.id }
                            ?.let {
                                it.name = newName
                                it.quantity = newQty
                            }
                    }
                )
            } else {
                ShoppingListItem(
                    item = shoppingItem,
                    onEditClick = {
                        shoppingItems.value = shoppingItems.value.map {
                            it.copy(isEditing = it.id == shoppingItem.id)
                        }
                        shoppingItems.value.find { it.id == shoppingItem.id }
                    },
                    onDeleteClick = {
                        shoppingItems.value -= shoppingItem
                    }
                )
            }
        }
    }
}

@Composable
fun AddShoppingItemView(
    showDialog: MutableState<Boolean>,
    shoppingItems: MutableState<List<ShoppingItem>>
) {
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = { showDialog.value = false },
        confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (itemName.isNotBlank()) {
                            val newItem = ShoppingItem(
                                id = shoppingItems.value.size + 1,
                                name = itemName,
                                quantity = itemQuantity.toIntOrNull() ?: 1
                            )
                            shoppingItems.value += newItem
                            showDialog.value = false
                            itemName = ""
                            itemQuantity = ""
                        }
                    }
                ) {
                    Text("Add")
                }
                Button(
                    onClick = { showDialog.value = false }
                ) {
                    Text("Cancel")
                }
            }
        },
        title = { Text("Add Shopping Item") },
        text = {
            Column {
                OutlinedTextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                OutlinedTextField(
                    value = itemQuantity,
                    onValueChange = { itemQuantity = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    )
}