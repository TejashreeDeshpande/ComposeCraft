package com.example.composecraft.presentation.components.easy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

//Color theme switcher
//Create a toggle that switches the screen background between two colors. Use MaterialTheme color tokens, not hardcoded hex values.
@Composable
fun ColorThemeSwitcher(modifier: Modifier = Modifier) {
    var dark by remember { mutableStateOf(true) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                if (dark)
                    MaterialTheme.colorScheme.background
                else
                    MaterialTheme.colorScheme.onBackground
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                dark = !dark
            }
        ) {
            Text(text = "Toggle")
        }
    }
}