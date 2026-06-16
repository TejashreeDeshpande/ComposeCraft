package com.example.composecraft.presentation.features.fittrack.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight


@Composable
fun FTTitle(
    title: String,
    style: TextStyle = FTTextStyle.listTitle,
    color: Color = FTTextColor.listTitle
) {
    Text(
        text = title,
        color = color,
        style = style,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
    )
}