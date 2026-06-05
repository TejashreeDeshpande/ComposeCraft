package com.example.composecraft.presentation.components.jun4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Feed(
    val id: Int,
    val title: String,
    val image: String,
    val category: String,
    val timestamp: String
)

@Composable
fun FeedListView(feedItems: List<Feed>) {
    LazyColumn {
        items(
            items = feedItems,
            key = { feed -> feed.id }) { item ->
            FeedListItem(item)
        }
    }
}

@Composable
fun FeedListItem(feed: Feed) {
    ListItem(
        leadingContent = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.Gray)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Preview,
                    contentDescription = ""
                )
            }
        },
        headlineContent = { Text(text = feed.title) },
        supportingContent = {
            val content = "${feed.category} . ${feed.timestamp}"
            Text(text = content)
        }
    )
}

@Preview
@Composable
fun FeedListViewPreView() {
    val items = listOf(
        Feed(
            id = 0,
            title = "Google announces major updated to Android 16 developer APIs",
            image = "",
            category = "The Verge",
            timestamp = "2 hours ago"
        ),
        Feed(
            id = 1,
            title = "Jetpack Compose 2.0 brings major performance improvements",
            image = "",
            category = "Android Developers",
            timestamp = "5 hours ago"
        ),
        Feed(
            id = 3,
            title = "Kotlin 2.2 - what's new for Android developers",
            image = "",
            category = "Kotlin Blog",
            timestamp = "1 day ago"
        )
    )
    FeedListView(feedItems = items)
}