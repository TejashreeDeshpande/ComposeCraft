package com.example.composecraft.presentation.features.vehicle.safetycontrols

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarCrash
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Headset
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Signpost
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

// --- Root Screen ---

@Composable
fun SafetyControlScreen(
    uiState: SafetyPanelUiState,
    onSafetyAction: (SafetyActionType) -> Unit,
    onConfirmPullOver: (PullOverOption) -> Unit,
    onSosHoldStart: () -> Unit,
    onSosHoldEnd: () -> Unit,
    onDismiss: () -> Unit
) {
    when (val state = uiState.safetyState) {
        is SafetyUiState.Idle -> {
            SafetyPanel(
                onAction = onSafetyAction,
                onDismiss = onDismiss
            )
        }
        is SafetyUiState.ConfirmPullOver -> {
            PullOverSheet(
                onConfirm = onConfirmPullOver,
                onDismiss = onDismiss
            )
        }
        is SafetyUiState.ConfirmEmergencyStop -> {
            EmergencyStopSheet(
                holdProgress = uiState.holdProgress,
                onHoldStart = onSosHoldStart,
                onHoldEnd = onSosHoldEnd,
                onDismiss = onDismiss
            )
        }
        else -> {
            // Active states rendered as banners inside the ride screen
            ActiveSafetyBanner(state = state)
        }
    }
}

// --- Screen 1: Safety Panel ---

@Composable
fun SafetyPanel(
    onAction: (SafetyActionType) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Safety controls",
                style = MaterialTheme.typography.titleMedium
            )
            IconButton(onClick = onDismiss) {
                Icon(Icons.Default.Close, contentDescription = "Close")
            }
        }

        RideHealthBanner()

        HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

        SafetyActionButton(
            icon = Icons.Default.CarCrash,
            iconTint = Color(0xFFE65100),
            iconBg = Color(0xFFFFF8E1),
            title = "Pull over",
            subtitle = "Ask vehicle to stop safely",
            onClick = { onAction(SafetyActionType.PULL_OVER) }
        )
        SafetyActionButton(
            icon = Icons.Default.Warning,
            iconTint = Color(0xFFC62828),
            iconBg = Color(0xFFFFEBEE),
            title = "Emergency stop",
            subtitle = "Immediately halt vehicle",
            onClick = { onAction(SafetyActionType.EMERGENCY_STOP) }
        )
        SafetyActionButton(
            icon = Icons.Default.Headset,
            iconTint = Color(0xFF1565C0),
            iconBg = Color(0xFFE3F2FD),
            title = "Contact support",
            subtitle = "Talk to a Waymo operator",
            onClick = { onAction(SafetyActionType.CONTACT_SUPPORT) }
        )
    }
}

@Composable
fun RideHealthBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFE8F5E9))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.VerifiedUser,
            contentDescription = null,
            tint = Color(0xFF2E7D32),
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Ride in progress · All systems nominal",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF2E7D32),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun SafetyActionButton(
    icon: ImageVector,
    iconTint: Color,
    iconBg: Color,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .border(0.5.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(iconBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(20.dp)
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
            Text(text = subtitle, style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// --- Screen 2: Pull Over Sheet ---

@Composable
fun PullOverSheet(
    onConfirm: (PullOverOption) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selected by remember { mutableStateOf(PullOverOption.IMMEDIATE) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        DragHandle()

        Text("Pull over nearby?", style = MaterialTheme.typography.titleMedium)
        Text(
            "The vehicle will find the nearest safe spot to stop. Your trip will be paused.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        PullOverOptionRow(
            icon = Icons.Default.Schedule,
            title = "Stop as soon as safe",
            subtitle = "Est. ~45 sec",
            isSelected = selected == PullOverOption.IMMEDIATE,
            onClick = { selected = PullOverOption.IMMEDIATE }
        )
        PullOverOptionRow(
            icon = Icons.Default.Signpost,
            title = "Pull over at next intersection",
            subtitle = "Est. ~2 min",
            isSelected = selected == PullOverOption.NEXT_INTERSECTION,
            onClick = { selected = PullOverOption.NEXT_INTERSECTION }
        )

        Button(
            onClick = { onConfirm(selected) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC62828))
        ) {
            Text("Confirm pull over")
        }
        TextButton(onClick = onDismiss, modifier = Modifier.fillMaxWidth()) {
            Text("Cancel")
        }
    }
}

@Composable
fun PullOverOptionRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) Color(0xFFE65100)
    else MaterialTheme.colorScheme.outlineVariant

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(if (isSelected) 1.5.dp else 0.5.dp, borderColor, RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(imageVector = icon, contentDescription = null,
            tint = Color(0xFFE65100), modifier = Modifier.size(18.dp))
        Column {
            Text(title, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
            Text(subtitle, style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

// --- Screen 3: Emergency Stop Sheet ---

@Composable
fun EmergencyStopSheet(
    holdProgress: Float,
    onHoldStart: () -> Unit,
    onHoldEnd: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Animate the sweep angle driven by holdProgress from ViewModel
    val animatedSweep by animateFloatAsState(
        targetValue = holdProgress * 360f,
        animationSpec = tween(50, easing = LinearEasing),
        label = "sos_sweep"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        DragHandle()

        Text(
            "Emergency stop",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFFC62828)
        )
        Text(
            "Hold the button for 3 seconds to stop the vehicle immediately. Use only in a genuine emergency.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        // SOS hold button with arc progress ring
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(90.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                // Track ring
                drawArc(
                    color = Color(0xFFFFCDD2),
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = 6.dp.toPx(), cap = StrokeCap.Round)
                )
                // Progress ring
                if (animatedSweep > 0f) {
                    drawArc(
                        color = Color(0xFFC62828),
                        startAngle = -90f,
                        sweepAngle = animatedSweep,
                        useCenter = false,
                        style = Stroke(width = 6.dp.toPx(), cap = StrokeCap.Round)
                    )
                }
            }

            // Hold-to-activate gesture detection
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFEBEE))
                    .border(2.dp, Color(0xFFC62828), CircleShape)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                onHoldStart()
                                tryAwaitRelease()
                                onHoldEnd()
                            }
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("SOS", style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFFC62828), fontWeight = FontWeight.Bold)
                    Text("Hold 3s", style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFFE53935))
                }
            }
        }

        Text(
            "Hold to confirm · releasing cancels",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        TextButton(onClick = onDismiss, modifier = Modifier.fillMaxWidth()) {
            Text("Cancel")
        }
    }
}

// --- Screen 4: Active Safety Banners (shown in ride screen) ---

@Composable
fun ActiveSafetyBanner(
    state: SafetyUiState,
    modifier: Modifier = Modifier
) {
    val (bgColor, borderColor, iconTint, icon, message) = when (state) {
        is SafetyUiState.PullingOver -> SafetyBannerStyle(
            bg = Color(0xFFFFF8E1), border = Color(0xFFFFB300),
            tint = Color(0xFFE65100), icon = Icons.Default.CarCrash,
            message = "Pulling over · Finding safe stop..."
        )
        is SafetyUiState.EmergencyStopped -> SafetyBannerStyle(
            bg = Color(0xFFFFEBEE), border = Color(0xFFE53935),
            tint = Color(0xFFC62828), icon = Icons.Default.Warning,
            message = "Emergency stop requested · Contacting support"
        )
        is SafetyUiState.SupportConnected -> SafetyBannerStyle(
            bg = Color(0xFFE8F5E9), border = Color(0xFF43A047),
            tint = Color(0xFF2E7D32), icon = Icons.Default.Check,
            message = "Vehicle stopped · Support is on the way"
        )
        else -> return
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(bgColor)
            .border(0.5.dp, borderColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(imageVector = icon, contentDescription = null,
            tint = iconTint, modifier = Modifier.size(16.dp))
        Text(message, style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium, color = iconTint)
    }
}

// Simple data holder for banner styling — avoids repeating the when block
data class SafetyBannerStyle(
    val bg: Color, val border: Color,
    val tint: Color, val icon: ImageVector,
    val message: String
)

// --- Shared ---

@Composable
fun DragHandle() {
    Box(
        modifier = Modifier
            .width(36.dp)
            .height(4.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(MaterialTheme.colorScheme.outlineVariant)
//            .align(Alignment.CenterHorizontally)
    )
}