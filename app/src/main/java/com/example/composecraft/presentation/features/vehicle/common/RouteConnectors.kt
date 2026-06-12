package com.example.composecraft.presentation.features.vehicle.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun RouteConnectorsPreview() {

    val locations = listOf("A", "B", "C", "D")
    RouteConnectors(locations)
}

@Composable
fun RouteConnectors(locations: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        locations.forEachIndexed { index, location ->
            RouteNode(location)
            if (index < locations.lastIndex)
                HorizontalDivider(Modifier.weight(1f),
                    DividerDefaults.Thickness, DividerDefaults.color)
//                RouteLine(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun RouteNode(text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .size(44.dp)
            .background(Color.Blue.copy(0.6f))
    ) {
        Text(
            text,
            color = Color.White
        )
    }
}

@Composable
fun RouteLine(
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .padding(horizontal = 4.dp)
            .height(2.dp)
    ) {
        drawLine(
            color = Color(0xFF333333),
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = size.height
        )
    }
}