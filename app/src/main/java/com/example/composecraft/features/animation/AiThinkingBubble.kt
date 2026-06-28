package com.example.composecraft.features.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun PreviewAIThinkingBubbleDemo() {
    AIThinkingBubbleDemo()
}
@Composable
fun AIThinkingBubbleDemo() {
    var isThinking by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Button(onClick = { isThinking = !isThinking }) {
            Text("Ask AI Coach")
        }

        AnimatedContent(
            targetState = isThinking,
            label = "thinking_content"
        ) { thinking ->
            if (thinking) {
                ThinkingBubble()
            } else {
                Text("Tap to generate workout insight")
            }
        }
    }
}

@Composable
private fun ThinkingBubble() {
    val transition = rememberInfiniteTransition(label = "dots")

    val dotCount by transition.animateFloat(
        initialValue = 1f,
        targetValue = 3f,
        animationSpec = infiniteRepeatable(
            tween(900),
            RepeatMode.Restart
        ),
        label = "dotCount"
    )

    Card(shape = RoundedCornerShape(24.dp)) {
        Text(
            text = "AI is thinking" + ".".repeat(dotCount.toInt()),
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.titleMedium
        )
    }
}