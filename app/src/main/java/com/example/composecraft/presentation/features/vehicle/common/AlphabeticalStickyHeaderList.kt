package com.example.composecraft.presentation.features.vehicle.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun AlphabeticalStickyHeaderListPreview() {
    val names = listOf(
        "Alice Smith",
        "Alexander Wright",
        "Amelia Jones",
        "Benjamin Carter",
        "Brooke Davis",
        "Charlotte Miller",
        "Connor Walsh",
        "Daniel Foster",
        "Daniela Reyes",
        "Elijah Brooks",
        "Emma Watson",
        "Ethan Hunt",
        "Fiona Gallagher",
        "Gabriel Garcia",
        "Grace Hopper",
        "Henry Cavill",
        "Isabella Ross",
        "Jack Ryan",
        "James Bond",
        "Katherine Pierce",
        "Liam Neeson",
        "Lucas Scott",
        "Mia Khalifa",
        "Noah Centineo",
        "Oliver Twist",
        "Penelope Cruz",
        "Quinn Fabray",
        "Ryan Reynolds",
        "Sophia Loren",
        "Thomas Shelby",
        "Uma Thurman",
        "Victoria Beckham",
        "William Shakespeare",
        "Xavier Woods",
        "Yusuf Islam",
        "Zachary Levi"
    )
    AlphabeticalStickyHeaderList(names)
}

@Composable
fun AlphabeticalStickyHeaderList(
    namesList: List<String>
) {
    val groupedItems by remember(namesList) {
        derivedStateOf {
            namesList
                .sorted()
                .groupBy { it.first().uppercaseChar() }
        }
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        groupedItems.forEach { (initial, names) ->
            stickyHeader {
                Text(
                    text = initial.toString(),
                )
            }
            items(names) { name ->
                Text(
                    text = name
                )
            }
        }
    }
}