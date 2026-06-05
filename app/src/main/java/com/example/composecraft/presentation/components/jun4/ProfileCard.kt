package com.example.composecraft.presentation.components.jun4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileCard(
    name: String,
    title: String,
    location: String,
    followers: Int,
    following: Int,
    bio: String
) {
    Card(modifier = Modifier.padding(16.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AvatarCircle("AR")
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = ".",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = location,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Row {
                        Text(
                            text = "$followers followers",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = ".",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = "$following following",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            Text(text = bio)
        }
    }
}

@Composable
fun AvatarCircle(initials: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(Color.Red)
    ) {
        Text(
            text = initials,
            color = Color.White
        )
    }
}
@Preview
@Composable
fun AvatarCirclePreview() {
    AvatarCircle("TD")
}
@Preview
@Composable
fun ProfileCardPreview() {
    ProfileCard(
        name = "Aish R",
        title = "Product Designer",
        location = "San Francisco",
        followers = 1200,
        following = 340,
        bio = "Designing products that people actually want to use. Currently at Nova Labs"
    )
}