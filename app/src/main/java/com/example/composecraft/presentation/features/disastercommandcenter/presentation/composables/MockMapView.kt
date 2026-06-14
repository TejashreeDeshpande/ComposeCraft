package com.example.composecraft.presentation.features.disastercommandcenter.presentation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MockMapView(modifier: Modifier = Modifier) {
    val roadColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
    val buildingColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f)
    val incidentColor = Color(0xFFEF4444) // Fire/Critical
    val floodColor = Color(0xFF3B82F6) // Flood
    val safeZoneColor = Color(0xFF22C55E) // Standby/Safe

    Box(modifier = modifier.background(MaterialTheme.colorScheme.surface)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Draw some "city blocks" or building shadows
            drawRect(
                color = buildingColor,
                topLeft = Offset(20f, 20f),
                size = androidx.compose.ui.geometry.Size(150f, 100f)
            )
            drawRect(
                color = buildingColor,
                topLeft = Offset(250f, 50f),
                size = androidx.compose.ui.geometry.Size(200f, 150f)
            )
            drawRect(
                color = buildingColor,
                topLeft = Offset(500f, 20f),
                size = androidx.compose.ui.geometry.Size(120f, 200f)
            )
            drawRect(
                color = buildingColor,
                topLeft = Offset(100f, 300f),
                size = androidx.compose.ui.geometry.Size(180f, 120f)
            )

            // Draw "Roads"
            val path = Path().apply {
                moveTo(0f, 200f)
                lineTo(size.width, 220f)
                
                moveTo(300f, 0f)
                lineTo(280f, size.height)
                
                moveTo(0f, 400f)
                quadraticTo(size.width / 2, 350f, size.width, 450f)
            }
            drawPath(
                path = path,
                color = roadColor,
                style = Stroke(width = 8f)
            )

            // Draw Incident Markers
            drawCircle(color = incidentColor, radius = 12f, center = Offset(150f, 180f))
            drawCircle(color = incidentColor.copy(alpha = 0.3f), radius = 24f, center = Offset(150f, 180f))

            drawCircle(color = floodColor, radius = 10f, center = Offset(450f, 350f))
            drawCircle(color = floodColor.copy(alpha = 0.3f), radius = 20f, center = Offset(450f, 350f))

            drawCircle(color = safeZoneColor, radius = 15f, center = Offset(600f, 100f))
        }
        
        // Add a "My Location" dot
        Surface(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 20.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primary,
            shadowElevation = 4.dp
        ) {
            Box(modifier = Modifier.padding(4.dp).background(Color.White, CircleShape)) {
                 Box(modifier = Modifier.padding(4.dp).background(MaterialTheme.colorScheme.primary, CircleShape))
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 200)
@Composable
fun PreviewMockMap() {
    MockMapView(modifier = Modifier.fillMaxSize())
}
