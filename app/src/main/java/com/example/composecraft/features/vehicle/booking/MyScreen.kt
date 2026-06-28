package com.example.composecraft.features.vehicle.booking

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.composecraft.ui.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@PreviewLightDark
@Composable
fun PreviewMyScreen() {
    AppTheme {
        MyScreen()
    }
}

@Composable
fun MyScreen() {
    // I will pass the state,
    // I will call the VM from the callbacks
    MyScreenContent()
}

data class User(
    val name: String,
    val id: String
)

val names = listOf(
    "Aarav",
    "Sophia",
    "Emma",
    "Liam",
    "Noah",
    "Olivia",
    "Mia",
    "Ethan"
)
val mockUsers = List(100) { index ->
    User(
        name = names.random(),
        id = "USR$1000 + $index"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreenContent() {
    Scaffold(
        containerColor = AppTheme.colors.background,
        topBar = {
            MyTopAppBar("Hello World")
        }
    ) { innerPadding ->
        var isRefreshing by remember { mutableStateOf(false) }
        val pullToRefreshState = rememberPullToRefreshState()
        val coroutineScope = rememberCoroutineScope()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .pullToRefresh(
                    isRefreshing = isRefreshing,
                    pullToRefreshState
                ) {
                    coroutineScope.launch {
                        isRefreshing = true
                        delay(1000)
                        isRefreshing = false
                    }
                }
        ) {
            var itemDetails: String? by remember { mutableStateOf(null) }
            val sheetState = rememberModalBottomSheetState()
            val scope = rememberCoroutineScope()
            var selectedFilter by remember {
                mutableStateOf<String?>(null)
            }
            val filteredUsers = remember(selectedFilter) {
                if (selectedFilter.isNullOrBlank()) {
                    mockUsers
                } else {
                    mockUsers.filter {
                        it.name.contains(selectedFilter!!, ignoreCase = true)
                    }
                }
            }

            Column {
                FlowRow(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    content = {
                        names.forEach { name ->
                            FilterChip(
                                selected = false,
                                onClick = {

                                },
                                label = {
                                    Text(
                                        name,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            )
                        }
                    }
                )
                LazyColumn {
                    items(filteredUsers) { user ->
                        ListItem(
                            modifier = Modifier.clickable {
                                itemDetails = user.name
                            },
                            colors = ListItemDefaults.colors(
                                containerColor = AppTheme.colors.background,
                                headlineColor = AppTheme.colors.onBackground
                            ),
                            headlineContent = {
                                Text(text = "This is ${user.name}")
                            }
                        )
                    }
                }
            }
            itemDetails?.let { item ->
                ModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = {
                        itemDetails = null
                    }
                ) {
                    Text(
                        text = "Details for item $item",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(title) },
    )
}
