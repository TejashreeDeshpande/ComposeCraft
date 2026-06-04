package com.example.composecraft.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

data class User(val name: String, val email: String)

@Composable
fun UserListView() {
    val users = listOf(
        User("Alice Johnson", "alice@email.com"),
        User("Bob Smith", "bob@email.com"),
        User("Carol White", "carol@email.com"),
        User("David Lee", "david@email.com"),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.inverseSurface
            )
    ) {
        items(users) { user ->
            UserCardItem(
                user.name,
                user.email
            )
        }
    }
}

@Composable
fun UserCardItem(
    name: String,
    email: String
) {
    ListItem(modifier = Modifier.background(color =MaterialTheme.colorScheme.inverseSurface),
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(shape = CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center,
            ) {
                Text(name.first().toString(), color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        },
        headlineContent = {
            Text(text = name, color = MaterialTheme.colorScheme.onSurfaceVariant)
        },
        supportingContent = {
            Text(text = email, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    )
}

@Preview
@Composable
fun UserCardItemPreview() {
    UserCardItem("Alice Johnson", "alice@email.com")
}

@Preview
@Composable
fun UserListPreview() {
    UserListView()
}