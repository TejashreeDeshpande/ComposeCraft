package com.example.composecraft.presentation.features.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun PreviewTypewriterHighlightBurstScreen() {
    TypewriterHighlightBurstScreen()
}
@Composable
fun TypewriterHighlightBurstScreen(
    modifier: Modifier = Modifier
) {
    val prompts = remember {
        listOf(
            "Plan Workout",
            "Suggest Meals",
            "Stay Motivated"
        )
    }

    val responses = remember {
        mapOf(
            "Plan Workout" to
                    "Today's workout focuses on strength and endurance. Start with a warm-up, complete three supersets, and finish with a short cardio burst.",

            "Suggest Meals" to
                    "Try a protein-rich breakfast, a balanced lunch with vegetables and grains, and a light dinner with healthy fats and lean protein.",

            "Stay Motivated" to
                    "Consistency beats intensity. Focus on showing up today, even if progress feels small. Every workout contributes to long-term success."
        )
    }

    var selectedPrompt by remember {
        mutableStateOf(prompts.first())
    }

    var visibleText by remember {
        mutableStateOf("")
    }

    var burstKey by remember {
        mutableIntStateOf(0)
    }

    val response = responses[selectedPrompt].orEmpty()

    LaunchedEffect(selectedPrompt) {
        visibleText = ""
        burstKey++

        response.forEachIndexed { index, _ ->
            visibleText = response.take(index + 1)
            delay(18)
        }
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            Text(
                text = "AI Coach",
                style = MaterialTheme.typography.headlineMedium
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                prompts.forEach { prompt ->

                    FilterChip(
                        selected = selectedPrompt == prompt,
                        onClick = {
                            selectedPrompt = prompt
                        },
                        label = {
                            Text(prompt)
                        }
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(24.dp)
            ) {

//                HighlightBurst(
//                    key = burstKey,
//                    modifier = Modifier.align(Alignment.Center)
//                )

                TypewriterText(
                    text = visibleText
                )
            }
        }
    }
}

@Composable
private fun HighlightBurst(
    key: Int,
    modifier: Modifier = Modifier
) {
    val scale = remember {
        Animatable(0.8f)
    }

    val alpha = remember {
        Animatable(0f)
    }

    LaunchedEffect(key) {

        alpha.snapTo(0.5f)
        scale.snapTo(0.8f)

        launch {
            scale.animateTo(
                targetValue = 2f,
                animationSpec = tween(
                    durationMillis = 600,
                    easing = FastOutSlowInEasing
                )
            )
        }

        launch {
            alpha.animateTo(
                targetValue = 0f,
                animationSpec = tween(600)
            )
        }
    }

    Box(
        modifier = modifier
            .size(180.dp)
            .graphicsLayer {
                scaleX = scale.value
                scaleY = scale.value
                this.alpha = alpha.value
            }
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                        Color.Transparent
                    )
                ),
                shape = CircleShape
            )
    )
}

@Composable
private fun TypewriterText(
    text: String
) {
    val transition = rememberInfiniteTransition(
        label = "cursor"
    )

    val cursorAlpha by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "cursorAlpha"
    )

    Text(
        text = buildAnnotatedString {
            append(text)

            withStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary.copy(
                        alpha = cursorAlpha
                    )
                )
            ) {
                append("▌")
            }
        },
        style = MaterialTheme.typography.bodyLarge
    )
}