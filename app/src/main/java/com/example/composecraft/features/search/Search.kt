package com.example.composecraft.features.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.ui.theme.FitTrackTheme

@Immutable
data class SearchListState<T>(
    val query: String = "",
    val selectedFilter: String = "All",
    val items: List<T> = emptyList(),
    val filters: List<String> = listOf("All"),
    val isLoading: Boolean = false
)

sealed interface SearchListAction<out T> {
    data class QueryChanged(val value: String) : SearchListAction<Nothing>
    data class FilterChanged(val value: String) : SearchListAction<Nothing>
    data class ItemClicked<T>(val item: T) : SearchListAction<T>
}

@Composable
fun <T> SearchableFilterableLazyColumn(
    state: SearchListState<T>,
    onAction: (SearchListAction<T>) -> Unit,
    modifier: Modifier = Modifier,
    key: (T) -> Any,
    itemMatchesQuery: (T, String) -> Boolean,
    itemMatchesFilter: (T, String) -> Boolean,
    itemContent: @Composable LazyItemScope.(T) -> Unit
) {
    val filterItems by remember(
        state.items,
        state.query,
        state.selectedFilter
    ) {
        derivedStateOf {
            state.items.filter { item ->
                itemMatchesQuery(item, state.query) &&
                        itemMatchesFilter(item, state.selectedFilter)
            }
        }
    }

    Column(modifier) {
        OutlinedTextField(
            value = state.query,
            onValueChange = {
                onAction(SearchListAction.QueryChanged(it))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search") },
            singleLine = true
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.filters) { filter ->
                FilterChip(
                    selected = filter == state.selectedFilter,
                    onClick = {
                        onAction(SearchListAction.FilterChanged(filter))
                    },
                    label = { Text(filter) }
                )
            }
        }
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = filterItems,
                    key = key
                ) { item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onAction(SearchListAction.ItemClicked(item))
                            }
                    ) {
                        itemContent(item)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchableFilterableLazyColumnPreview() {
    data class MockItem(val id: Int, val name: String, val category: String)

    val items = listOf(
        MockItem(1, "Apple", "Fruit"),
        MockItem(2, "Banana", "Fruit"),
        MockItem(3, "Carrot", "Vegetable"),
        MockItem(4, "Date", "Fruit"),
        MockItem(5, "Eggplant", "Vegetable")
    )

    var state by remember {
        mutableStateOf(
            SearchListState(
                items = items,
                filters = listOf("All", "Fruit", "Vegetable")
            )
        )
    }

    FitTrackTheme {
        Surface {
            SearchableFilterableLazyColumn(
                state = state,
                onAction = { action ->
                    when (action) {
                        is SearchListAction.QueryChanged -> {
                            state = state.copy(query = action.value)
                        }

                        is SearchListAction.FilterChanged -> {
                            state = state.copy(selectedFilter = action.value)
                        }

                        is SearchListAction.ItemClicked -> {
                            // Handle item click
                        }
                    }
                },
                key = { it.id },
                itemMatchesQuery = { item, query ->
                    item.name.contains(query, ignoreCase = true)
                },
                itemMatchesFilter = { item, filter ->
                    filter == "All" || item.category == filter
                },
                itemContent = { item ->
                    Text(
                        text = "${item.name} (${item.category})",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            )
        }
    }
}
