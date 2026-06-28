package com.example.composecraft.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.ui.navigation.*
import androidx.navigation3.runtime.NavKey

data class Feature(
    val name: String,
    val description: String,
    val icon: String,
    val destination: NavKey,
    val color: Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeatureDashboardScreen(onFeatureClick: (NavKey) -> Unit) {
    val features = listOf(
        Feature("Instacart", "Grocery delivery with cart & checkout", "🛒", InstacartDestination, Color(0xFF00A74A)),
        Feature("Pulse Invest", "Fintech app with stock tracking", "📈", PulseDestination, Color(0xFF00FF88)),
        Feature("Flight Status", "Real-time flight tracking UI", "✈️", FlightDestination, Color(0xFF4488FF)),
        Feature("Disaster Command", "Emergency response dashboard", "🚨", DisasterDestination, Color(0xFFFF4444)),
        Feature("FitTrack", "Workout builder and tracker", "💪", FitTrackDestination, Color(0xFFAA44FF)),
        Feature("Vehicle Progress", "Automotive UI components", "🚗", VehicleDestination, Color(0xFFFFCC00)),
        Feature("Vehicle Sensors", "Real-time sensor telemetry", "📡", SensorDestination, Color(0xFF4CAF50)),
        Feature("Ride Status", "Track your active Waymo ride", "🚕", RideStatusDestination, Color(0xFF2196F3)),
        Feature("Vehicle Alerts", "System notifications", "🔔", VehicleNotificationsDestination, Color(0xFFFF9800)),
        Feature("Safety Controls", "Emergency vehicle actions", "🛡️", SafetyControlDestination, Color(0xFFF44336)),
        Feature("Trip History", "View past rides & ratings", "📜", TripHistoryDestination, Color(0xFFFFA500)),
        Feature("Animations", "Gallery of Compose animations", "✨", AnimationsDestination, Color(0xFF00FFFF))
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ComposeCraft Dashboard", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF121212),
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color(0xFF121212)
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            items(features) { feature ->
                FeatureCard(feature, onFeatureClick)
            }
        }
    }
}

@Composable
fun FeatureCard(feature: Feature, onClick: (NavKey) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(feature.color.copy(alpha = 0.15f), Color(0xFF1E1E1E))
                )
            )
            .clickable { onClick(feature.destination) }
            .padding(20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(feature.color.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(feature.icon, fontSize = 28.sp)
            }
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    text = feature.name,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = feature.description,
                    color = Color.Gray,
                    fontSize = 13.sp
                )
            }
            Spacer(Modifier.weight(1f))
            Text("→", color = feature.color, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}
