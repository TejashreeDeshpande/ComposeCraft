package com.example.composecraft.features.animation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun PreviewAIConversationTimelineDemo() {
    AIConversationTimelineDemo()
}
@Composable
fun AIConversationTimelineDemo() {
    var messages by remember {
        mutableStateOf(listOf("Hi, I am your AI coach."))
    }

    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = {
                messages = messages + "Great job today. Your consistency is improving."
            }
        ) {
            Text("Generate Message")
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(messages) { message ->
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(MaterialTheme.colorScheme.primary, CircleShape)
                    )

                    Card {
                        Text(
                            text = message,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}