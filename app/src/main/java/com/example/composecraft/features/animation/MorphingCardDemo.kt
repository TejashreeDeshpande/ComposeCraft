package com.example.composecraft.features.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
fun PreviewMorphingCardDemo() {
    MorphingCardDemo()
}
@Composable
fun MorphingCardDemo() {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (expanded) 420.dp else 140.dp)
                .clickable { expanded = !expanded }
                .animateContentSize(),
            shape = RoundedCornerShape(if (expanded) 32.dp else 20.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Full Body Strength", style = MaterialTheme.typography.headlineSmall)
                Text("45 min • 8 exercises")

                AnimatedVisibility(expanded) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("• Squats")
                        Text("• Push Ups")
                        Text("• Lunges")
                        Text("• Plank")
                        Text("• Cooldown")
                    }
                }
            }
        }
    }
}