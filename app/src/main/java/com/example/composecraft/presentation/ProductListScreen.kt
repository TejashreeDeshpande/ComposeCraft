package com.example.composecraft.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composecraft.data.Product
import com.example.composecraft.data.mockProducts
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen() {
    val products = remember { mutableStateListOf(*mockProducts.toTypedArray()) }
    var searchQuery by remember { mutableStateOf("") }
    val queryFlow = snapshotFlow { searchQuery }
    val filteredProducts by remember {
        queryFlow
            .map { text ->
                products.filter { it.name.startsWith(text, ignoreCase = true) }
            }
    }.collectAsState(initial = emptyList())

    val totalCount by remember { derivedStateOf { products.size } }
    val filteredCount by remember { derivedStateOf { filteredProducts.size } }
    val countText by remember {
        derivedStateOf {
            if (searchQuery.isEmpty()) {
                "Total products: $totalCount"
            } else {
                "Showing $filteredCount of $totalCount products"
            }
        }
    }


    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text(text = "Compose craft")
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Product"
                )
            }
        }) { innerPadding ->
        // Display Add Product Alert
        Box(modifier = Modifier.padding(innerPadding)) {
            if (showDialog) {
                AddNewProductDialog(
                    onConfirmAdd = { productName ->
                        showDialog = false
                        val index = products.size + 1
                        val newProduct = Product(
                            id = "$index",
                            name = productName
                        )
                        products.add(newProduct)
                    },
                    onDismissAdd = {
                        showDialog = false
                    })
            }
        }
        // List View
        ProductListContents(
            modifier = Modifier.padding(innerPadding),
            products = if (searchQuery.isEmpty()) products else {
                filteredProducts
            },
            onClickFavorite = { product ->
                val index = products.indexOfFirst { it.id == product.id }
                if (index != -1) {
                    products[index] =
                        products[index].copy(favorite = !products[index].favorite)
                }
            },
            onDeleteClick = { product ->
                val index = products.indexOfFirst { it.id == product.id }
                if (index != -1) {
                    products.remove(product)
                }
            },
            query = searchQuery,
            onQueryChange = { newValue ->
                searchQuery = newValue
            },
            countText = countText
        )
    }
}

@Composable
fun AddNewProductDialog(
    onConfirmAdd: (String) -> Unit,
    onDismissAdd: () -> Unit
) {
    var productName by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = {
            onDismissAdd()
        },
        confirmButton = {
            TextButton(onClick = {
                if (!productName.isEmpty()) {
                    onConfirmAdd(productName)
                }
            }) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismissAdd()
            }) {
                Text("Dismiss")
            }
        },
        title = {
            Text("Add New Product")
        },
        text = {
            TextField(
                value = productName,
                onValueChange = { newValue ->
                    productName = newValue
                }
            )
        })

}

@Composable
fun ProductListContents(
    modifier: Modifier,
    products: List<Product>,
    onClickFavorite: (Product) -> Unit,
    onDeleteClick: (Product) -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
    countText: String
) {

    Column(modifier = modifier.fillMaxSize()) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            value = query,
            onValueChange = {
                onQueryChange(it)
            }
        )

        Text(
            text = countText,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            items(products) { product ->
                ListItem(
                    headlineContent = {
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = product.name, modifier = Modifier.weight(1f))
                            IconButton(
                                onClick = {
                                    onClickFavorite(product)
                                },
                                modifier = Modifier.wrapContentSize()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    tint = if (product.favorite) Color.Red else Color.Gray,
                                    contentDescription = "Favorite"
                                )
                            }
                        }
                    },
                    trailingContent = {
                        IconButton(onClick = { onDeleteClick(product) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Product",
                                tint = Color.Red
                            )
                        }
                    }
                )
            }
        }
    }
}
