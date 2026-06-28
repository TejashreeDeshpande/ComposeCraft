package com.example.composecraft.features.fittrack.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun FTCircleIcon(
    iconStr: String,
    iconSize: Dp,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    onClickActionButton: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(iconSize)
            .clip(CircleShape)
            .background(containerColor)
            .clickable(onClick = onClickActionButton)
    ) {
        Text(text = iconStr, color = contentColor)
    }
}

