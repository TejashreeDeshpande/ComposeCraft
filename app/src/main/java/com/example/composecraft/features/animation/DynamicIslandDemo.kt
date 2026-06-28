package com.example.composecraft.features.animation

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun PreviewDynamicIslandDemo() {
    DynamicIslandDemo()
}
@Composable
fun DynamicIslandDemo() {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Card(
            modifier = Modifier
                .padding(top = 40.dp)
                .clickable { expanded = !expanded }
                .animateContentSize(),
            shape = RoundedCornerShape(50)
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = if (expanded) 28.dp else 18.dp,
                    vertical = if (expanded) 18.dp else 10.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(if (expanded) "🏋 Workout Completed" else "🏋")
                if (expanded) {
                    Text("+120 XP")
                    Text("7 Day Streak")
                }
            }
        }
    }
}