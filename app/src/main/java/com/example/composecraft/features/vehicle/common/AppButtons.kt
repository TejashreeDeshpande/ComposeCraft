package com.example.composecraft.features.vehicle.common

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AppButtonsPreview() {
    AppButtons()
}

@Composable
fun AppButtons() {
    Button(onClick = {}) {
        Text(text = "Button")
    }

}