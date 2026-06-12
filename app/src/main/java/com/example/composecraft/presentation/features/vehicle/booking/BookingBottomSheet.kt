package com.example.composecraft.presentation.features.vehicle.booking

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.TripOrigin
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

// --- Root Bottom Sheet ---

@Composable
fun BookingConfirmationSheet(
    booking: BookingDetails,
    confirmationState: BookingConfirmationState,
    onCancelBooking: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        shadowElevation = 16.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            DragHandle()

            BookingHeader(state = confirmationState)

            CountdownTimer(state = confirmationState)

            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

            BookingAddressRows(booking = booking)

            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

            BookingFareSummary(booking = booking)

            CancelButton(
                state = confirmationState,
                onCancelBooking = onCancelBooking
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// --- Drag Handle ---

@Composable
fun DragHandle() {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(4.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(Color.LightGray)
    )
}

// --- Header ---

@Composable
fun BookingHeader(state: BookingConfirmationState) {
    val subtitle = when (state) {
        is BookingConfirmationState.Cancellable -> "Dispatching your Waymo..."
        is BookingConfirmationState.Confirmed   -> "Your Waymo is on the way"
        is BookingConfirmationState.Cancelled   -> "Booking cancelled"
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = "Ride Confirmed",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// --- Circular Countdown Timer ---

@Composable
fun CountdownTimer(
    state: BookingConfirmationState,
    modifier: Modifier = Modifier
) {
    val TOTAL_SECONDS = 10

    // Animate the sweep angle whenever secondsRemaining changes
    val targetSweep = when (state) {
        is BookingConfirmationState.Cancellable ->
            (state.secondsRemaining / TOTAL_SECONDS.toFloat()) * 360f
        else -> 0f
    }

    val animatedSweep by animateFloatAsState(
        targetValue = targetSweep,
        animationSpec = tween(durationMillis = 800, easing = LinearEasing),
        label = "countdown_sweep"
    )

    val arcColor = when {
        state is BookingConfirmationState.Cancellable &&
                state.secondsRemaining <= 3 -> Color(0xFFE53935)   // urgent red
        state is BookingConfirmationState.Cancellable -> Color(0xFF1E88E5)
        else -> Color.LightGray
    }

    val animatedArcColor by animateColorAsState(
        targetValue = arcColor,
        label = "arc_color"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(96.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Background track
            drawArc(
                color = Color.LightGray.copy(alpha = 0.3f),
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(
                    width = 8.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
            // Animated foreground arc
            drawArc(
                color = animatedArcColor,
                startAngle = -90f,
                sweepAngle = animatedSweep,
                useCenter = false,
                style = Stroke(
                    width = 8.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }

        // Center label
        when (state) {
            is BookingConfirmationState.Cancellable -> {
                Text(
                    text = state.secondsRemaining.toString(),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            is BookingConfirmationState.Confirmed -> {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Confirmed",
                    tint = Color(0xFF2E7D32),
                    modifier = Modifier.size(36.dp)
                )
            }
            is BookingConfirmationState.Cancelled -> {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Cancelled",
                    tint = Color(0xFFE53935),
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
}

// --- Address Rows ---

@Composable
fun BookingAddressRows(booking: BookingDetails) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        AddressRow(
            icon = Icons.Default.TripOrigin,
            iconTint = Color(0xFF1E88E5),
            label = "Pickup",
            address = booking.pickupAddress
        )
        AddressRow(
            icon = Icons.Default.LocationOn,
            iconTint = Color(0xFFE53935),
            label = "Dropoff",
            address = booking.dropoffAddress
        )
    }
}

@Composable
fun AddressRow(
    icon: ImageVector,
    iconTint: Color,
    label: String,
    address: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(20.dp)
        )
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = address,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

// --- Fare Summary ---

@Composable
fun BookingFareSummary(booking: BookingDetails) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        FareSummaryItem(
            label = "Est. Fare",
            value = booking.estimatedFare
        )
        VerticalDivider(
            modifier = Modifier.height(36.dp)
        )
        FareSummaryItem(
            label = "ETA",
            value = "${booking.etaMinutes} min"
        )
    }
}

@Composable
fun FareSummaryItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}

// --- Cancel Button ---

@Composable
fun CancelButton(
    state: BookingConfirmationState,
    onCancelBooking: () -> Unit
) {
    val isEnabled = state is BookingConfirmationState.Cancellable

    TextButton(
        onClick = onCancelBooking,
        enabled = isEnabled
    ) {
        Text(
            text = "Cancel Booking",
            color = if (isEnabled) Color(0xFFE53935)
            else Color.LightGray,
            style = MaterialTheme.typography.labelLarge
        )
    }
}