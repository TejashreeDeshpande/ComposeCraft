package com.example.composecraft.features.vehicle.sensors

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.Radar
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WifiTethering
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// --- Data / State ---

enum class SensorStatus { NOMINAL, DEGRADED, OFFLINE }

enum class SensorType { LIDAR, CAMERA, RADAR, GPS }

data class Sensor(
    val id: String,
    val name: String,
    val type: SensorType,
    val status: SensorStatus,
    val signalStrength: Int,      // 0–5
    val lastUpdatedMs: Long
)

data class SensorDashboardUiState(
    val isLiveConnected: Boolean = true,
    val sensors: List<Sensor> = emptyList(),
    val expandedSensorId: String? = null     // one card open at a time
)

// --- Root Screen ---

@Composable
fun SensorDashboardScreen(
    uiState: SensorDashboardUiState,
    onSensorCardTap: (sensorId: String) -> Unit,
    onBack: () -> Unit = {}
) {
    val hasAlert = remember(uiState.sensors) {
        uiState.sensors.any {
            it.status == SensorStatus.OFFLINE || it.status == SensorStatus.DEGRADED
        }
    }

    Scaffold(
        topBar = {
            SensorTopBar(isLive = uiState.isLiveConnected, onBack = onBack)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SensorGrid(
                sensors = uiState.sensors,
                expandedSensorId = uiState.expandedSensorId,
                onSensorCardTap = onSensorCardTap,
                // Extra bottom padding so banner doesn't cover last row
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = if (hasAlert) 80.dp else 16.dp
                )
            )

            AnimatedVisibility(
                visible = hasAlert,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                SensorAlertBanner()
            }
        }
    }
}

// --- Top Bar with Live Indicator ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SensorTopBar(isLive: Boolean, onBack: () -> Unit = {}) {
    TopAppBar(
        title = { Text("Vehicle Sensors") },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            LiveConnectionDot(isLive = isLive)
            Spacer(modifier = Modifier.width(16.dp))
        }
    )
}

@Composable
fun LiveConnectionDot(isLive: Boolean) {
    val infiniteTransition = rememberInfiniteTransition(label = "live_pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )

    val color = if (isLive) Color(0xFF4CAF50) else Color.Gray

    Box(
        modifier = Modifier
            .size(12.dp)
            .scale(if (isLive) scale else 1f)
            .clip(CircleShape)
            .background(color)
    )
}

// --- Sensor Grid ---

@Composable
fun SensorGrid(
    sensors: List<Sensor>,
    expandedSensorId: String?,
    onSensorCardTap: (String) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = sensors,
            key = { it.id }
        ) { sensor ->
            SensorCard(
                sensor = sensor,
                isExpanded = sensor.id == expandedSensorId,
                onTap = { onSensorCardTap(sensor.id) }
            )
        }
    }
}

// --- Sensor Card ---

@Composable
fun SensorCard(
    sensor: Sensor,
    isExpanded: Boolean,
    onTap: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = when (sensor.status) {
        SensorStatus.NOMINAL  -> Color.Transparent
        SensorStatus.DEGRADED -> Color(0xFFFFA000)
        SensorStatus.OFFLINE  -> Color(0xFFE53935)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onTap)
            .border(
                width = 1.5.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Icon + Name
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = sensorIcon(sensor.type),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = sensor.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            SensorStatusBadge(status = sensor.status)

            SignalStrengthBar(strength = sensor.signalStrength)

            // Expandable detail row
            AnimatedVisibility(visible = isExpanded) {
                Text(
                    text = "Updated ${formatTimestamp(sensor.lastUpdatedMs)}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// --- Status Badge ---

@Composable
fun SensorStatusBadge(status: SensorStatus) {
    val (label, bgColor, textColor) = when (status) {
        SensorStatus.NOMINAL  -> Triple("NOMINAL",  Color(0xFFE8F5E9), Color(0xFF2E7D32))
        SensorStatus.DEGRADED -> Triple("DEGRADED", Color(0xFFFFF8E1), Color(0xFFF57F17))
        SensorStatus.OFFLINE  -> Triple("OFFLINE",  Color(0xFFFFEBEE), Color(0xFFC62828))
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(bgColor)
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}

// --- Signal Strength Bar ---

@Composable
fun SignalStrengthBar(
    strength: Int,            // 0–5
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        (1..5).forEach { bar ->
            val filled = bar <= strength
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .height((4 + bar * 3).dp)     // each bar is taller than the last
                    .clip(RoundedCornerShape(topStart = 2.dp, topEnd = 2.dp))
                    .background(
                        if (filled) MaterialTheme.colorScheme.primary
                        else Color.LightGray
                    )
            )
        }
    }
}

// --- Alert Banner ---

@Composable
fun SensorAlertBanner(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color(0xFFB71C1C),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "One or more sensors need attention",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// --- Helpers ---

fun sensorIcon(type: SensorType): ImageVector = when (type) {
    SensorType.LIDAR  -> Icons.Default.Radar
    SensorType.CAMERA -> Icons.Default.PhotoCamera
    SensorType.RADAR  -> Icons.Default.WifiTethering
    SensorType.GPS    -> Icons.Default.LocationOn
}

fun formatTimestamp(ms: Long): String {
    val seconds = (System.currentTimeMillis() - ms) / 1000
    return when {
        seconds < 60 -> "just now"
        seconds < 3600 -> "${seconds / 60}m ago"
        seconds < 86400 -> "${seconds / 3600}h ago"
        else -> "${seconds / 86400}d ago"
    }
}

@Preview(showBackground = true)
@Composable
fun SensorDashboardPreview() {
    val sampleSensors = listOf(
        Sensor("1", "Front LiDAR", SensorType.LIDAR, SensorStatus.NOMINAL, 5, System.currentTimeMillis()),
        Sensor("2", "Rear Camera", SensorType.CAMERA, SensorStatus.DEGRADED, 3, System.currentTimeMillis() - 120_000),
        Sensor("3", "Side Radar", SensorType.RADAR, SensorStatus.OFFLINE, 0, System.currentTimeMillis() - 3_600_000),
        Sensor("4", "Global GPS", SensorType.GPS, SensorStatus.NOMINAL, 4, System.currentTimeMillis() - 60_000)
    )
    MaterialTheme {
        SensorDashboardScreen(
            uiState = SensorDashboardUiState(
                sensors = sampleSensors,
                expandedSensorId = "2"
            ),
            onSensorCardTap = {}
        )
    }
}
