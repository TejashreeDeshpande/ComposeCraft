package com.example.composecraft.presentation.components.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun PreviewCounterAnimationDemo() {
    CounterAnimationDemo()
}

@Composable
fun CounterAnimationDemo(modifier: Modifier = Modifier) {

    var count by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedContent(
            targetState = count,
            label = "counter"
        ) { value ->
            Text(
                text = value.toString(),
                fontSize = 48.sp
            )
        }

        Button(
            onClick = { count++ }
        ) {
            Text("Increment")
        }
    }
}