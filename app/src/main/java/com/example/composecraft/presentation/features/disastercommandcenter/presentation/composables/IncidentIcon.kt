package com.example.composecraft.presentation.features.disastercommandcenter.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.IconSpec

@Composable
fun IncidentIcon(
    icon: IconSpec,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .size(36.dp)
            .background(icon.color.copy(alpha = 0.1f))
            .padding(horizontal = 12.dp, vertical = 4.dp)

    ) {
        Text(
            text = icon.emoji,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 12.sp
            ),
            color = icon.color,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun IncidentLabel(
    icon: IconSpec,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RectangleShape)
            .background(icon.color.copy(alpha = 0.1f))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = icon.emoji,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 12.sp
            ),
            color = icon.color,
            fontWeight = FontWeight.Bold
        )
    }
}
