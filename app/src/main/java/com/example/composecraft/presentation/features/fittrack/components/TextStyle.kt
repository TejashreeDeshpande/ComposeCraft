package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

object FTTextStyle {
    val listTitle: TextStyle
        @Composable get() = MaterialTheme.typography.headlineMedium

    val rowTitle: TextStyle
        @Composable get() = MaterialTheme.typography.titleMedium
}

object FTTextColor {
    val listTitle: Color
        @Composable get() = MaterialTheme.colorScheme.primary

    val rowTitle: Color
        @Composable get() = MaterialTheme.colorScheme.onSurface
}