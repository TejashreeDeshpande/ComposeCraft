package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun WorkoutListItemPreview() {
    WorkoutListItem(
        title = "Push Day",
        subTitle = "3 exercises * 2 days ago",
        iconStr = "▶",
        iconBackground = Color.Red,
        onClickIcon = {}
    )
}

@Composable
fun WorkoutListItem(
    title: String,
    subTitle: String,
    iconStr: String,
    iconBackground: Color,
    modifier: Modifier = Modifier,
    onClickIcon: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, Color.Gray.copy(alpha = 0.3f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = {
                onClickIcon()
            }) {
                Box(modifier = modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(iconBackground)
                )
                Text(text = iconStr)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                )
                Text(
                    text = subTitle
                )
            }
        }
    }
}
