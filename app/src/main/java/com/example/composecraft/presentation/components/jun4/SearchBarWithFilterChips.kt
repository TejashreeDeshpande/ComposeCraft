package com.example.composecraft.presentation.components.jun4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class RecipesCategory {
    All,
    Italian,
    Breakfast,
    Thai,
    Vegan
}

data class Recipe(
    val id: Int,
    val name: String,
    val distanceInMin: Int,
    val category: RecipesCategory,
    val calories: Int
)

val recipes = listOf(
    Recipe(
        id = 1,
        name = "Pasta carbonara",
        distanceInMin = 25,
        category = RecipesCategory.Italian,
        calories = 450
    ),
    Recipe(
        id = 2,
        name = "Avocado toast",
        distanceInMin = 10,
        category = RecipesCategory.Breakfast,
        calories = 320
    ),
    Recipe(
        id = 3,
        name = "Thai green curry",
        distanceInMin = 40,
        category = RecipesCategory.Thai,
        calories = 520
    ),
)

@Composable
fun SearchBarWithFilterChips(items: List<Recipe> = recipes) {
    Scaffold { paddingValues ->
        SearchBarWithFilterChipsContents(
            items,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun SearchBarWithFilterChipsContents(
    recipesList: List<Recipe>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            CategoriesChips()
        }
        items(
            items = recipesList,
            key = { it.id }) { item ->
            RecipeListItem(item)
        }
    }
}

@Composable
fun CategoriesChips() {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(RecipesCategory.entries.toTypedArray()) {
            FilterChip(
                selected = true,
                onClick = {
                },
                label = { Text(text = it.name) },
                shape = RoundedCornerShape(8.dp)
            )
        }
    }
}

@Composable
fun RecipeListItem(item: Recipe) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleAvatar(item.name.first().toString())
        Spacer(Modifier.width(16.dp))

        Column {
            Text(text = item.name)
            Row {
                Text(text = "${item.distanceInMin} min")
                DotDivider()
                Text(text = "${item.distanceInMin} min")
                DotDivider()
                Text(text = "${item.calories} cal")
            }
        }
    }
}

@Composable
fun DotDivider() {
    Spacer(Modifier.width(1.dp))
    Text(".")
    Spacer(Modifier.width(1.dp))
}

@Composable
fun CircleAvatar(
    initials: String,
    bgColor: Color = Color.Black,
    textColor: Color = Color.White,
    size: Dp = 36.dp
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(bgColor)
    ) {
        Text(
            text = initials,
            color = textColor
        )
    }
}

@Preview
@Composable
fun SearchBarWithFilterChipsPreview() {

    SearchBarWithFilterChipsContents(
        listOf(
            Recipe(
                id = 1,
                name = "Pasta carbonara",
                distanceInMin = 25,
                category = RecipesCategory.Italian,
                calories = 450
            ),
            Recipe(
                id = 1,
                name = "Avocado toast",
                distanceInMin = 10,
                category = RecipesCategory.Breakfast,
                calories = 320
            ),
            Recipe(
                id = 1,
                name = "Thai green curry",
                distanceInMin = 40,
                category = RecipesCategory.Thai,
                calories = 520
            )
        )
    )
}