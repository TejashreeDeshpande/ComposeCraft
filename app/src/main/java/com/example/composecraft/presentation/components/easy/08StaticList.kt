package com.example.composecraft.presentation.components.easy

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


data class City(
    val name: String,
    val flag: String
)

@Composable
fun StaticList(
    cities: List<City>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(cities) { city ->
            ListItem(
                headlineContent = {
                    Text(text = city.name)
                },
                trailingContent = {
                    Text(text = city.flag)
                })
        }
    }
}

@Preview
@Composable
fun StaticListPreview() {
    val mockCities = listOf(
        City(name = "Tokyo", flag = "🇯🇵"),
        City(name = "Paris", flag = "🇫🇷"),
        City(name = "New York", flag = "🇺🇸"),
        City(name = "London", flag = "🇬🇧"),
        City(name = "Rio de Janeiro", flag = "🇧🇷"),
        City(name = "Sydney", flag = "🇦🇺"),
        City(name = "Cairo", flag = "🇪🇬"),
        City(name = "Mumbai", flag = "🇮🇳"),
        City(name = "Toronto", flag = "🇨🇦"),
        City(name = "Berlin", flag = "🇩🇪")
    )
    StaticList(mockCities)
}
