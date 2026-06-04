package com.example.composecraft.presentation.components.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SlideCounterDemo(modifier: Modifier = Modifier) {

    var count by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AnimatedContent(
            targetState = count,
            transitionSpec = {

                slideInVertically {
                    it
                } + fadeIn() togetherWith

                        slideOutVertically {
                            -it
                        } + fadeOut()
            },
            label = "slideCounter"
        ) { value ->

            Text(
                text = value.toString(),
                fontSize = 50.sp
            )
        }

        Button(
            onClick = { count++ }
        ) {
            Text("Next")
        }
    }
}