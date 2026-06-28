package com.example.composecraft.features.vehicle.ridestatus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// --- Data / State ---

enum class RideStatus { ARRIVING, ARRIVED, IN_PROGRESS }

data class RideUiState(
    val status: RideStatus = RideStatus.ARRIVING,
    val etaMinutes: Int = 3,
    val vehicleId: String = "W-001",
    val rating: Float = 4.9f
)

// --- Root Screen ---

@Composable
fun RideStatusScreen(
    uiState: RideUiState,
    onBack: () -> Unit,
    onContact: () -> Unit,
    onCancelRide: () -> Unit
) {
    Scaffold(
        topBar = {
            RideTopBar(onBack = onBack)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Map layer
            MapPlaceholder(modifier = Modifier.fillMaxSize())

            // Bottom sheet anchored to bottom
            RideBottomSheet(
                uiState = uiState,
                onContact = onContact,
                onCancelRide = onCancelRide,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

// --- Top Bar ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideTopBar(onBack: () -> Unit) {
    TopAppBar(
        title = { Text("Your Ride") },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}

// --- Map Placeholder ---

@Composable
fun MapPlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Map",
            color = Color.DarkGray,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

// --- Bottom Sheet ---

@Composable
fun RideBottomSheet(
    uiState: RideUiState,
    onContact: () -> Unit,
    onCancelRide: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RideStatusRow(uiState)

            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

            VehicleInfoRow(uiState)

            RideActionButtons(
                onContact = onContact,
                onCancelRide = onCancelRide
            )
        }
    }
}

// --- ETA / Status Row ---

@Composable
fun RideStatusRow(uiState: RideUiState) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.DirectionsCar,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = when (uiState.status) {
                RideStatus.ARRIVING -> "Waymo is ${uiState.etaMinutes} mins away"
                RideStatus.ARRIVED  -> "Your Waymo has arrived"
                RideStatus.IN_PROGRESS -> "Heading to your destination"
            },
            style = MaterialTheme.typography.titleMedium
        )
    }
}

// --- Vehicle Info Row ---

@Composable
fun VehicleInfoRow(uiState: RideUiState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Avatar placeholder
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            Text(
                text = uiState.vehicleId,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        // Star rating
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = uiState.rating.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// --- Action Buttons ---

@Composable
fun RideActionButtons(
    onContact: () -> Unit,
    onCancelRide: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedButton(
            onClick = onContact,
            modifier = Modifier.weight(1f)
        ) {
            Text("Contact")
        }

        Button(
            onClick = onCancelRide,
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            Text("Cancel Ride")
        }
    }
}