package com.example.composecraft.presentation.features.flightstatus.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.ui.theme.FlightStatus

data class FlightJourneyStep(
    val status: FlightStatus,
    val progress: Float,
    val durationMillis: Long,
    val emoji: String
)

val flightJourney = listOf(
    FlightJourneyStep(FlightStatus.SCHEDULED, 0.05f, 1200, "📅"),
    FlightJourneyStep(FlightStatus.CHECK_IN, 0.10f, 1200, "🎫"),
    FlightJourneyStep(FlightStatus.SECURITY, 0.15f, 1200, "🛂"),
    FlightJourneyStep(FlightStatus.BOARDING, 0.25f, 1500, "🛫"),
    FlightJourneyStep(FlightStatus.BOARDING, 0.35f, 1200, "🚪"),
    FlightJourneyStep(FlightStatus.DEPARTED, 0.45f, 1200, "↩️"),
    FlightJourneyStep(FlightStatus.DEPARTED, 0.55f, 1500, "🚕"),
    FlightJourneyStep(FlightStatus.IN_AIR, 0.65f, 1500, "🚀"),
    FlightJourneyStep(FlightStatus.IN_AIR, 0.75f, 1500, "☁️"),
    FlightJourneyStep(FlightStatus.IN_AIR, 0.85f, 1800, "✈️"),
    FlightJourneyStep(FlightStatus.LANDING, 0.92f, 1500, "📉"),
    FlightJourneyStep(FlightStatus.LANDING, 0.97f, 1500, "🛬"),
    FlightJourneyStep(FlightStatus.LANDED, 1.00f, 1800, "🏁")
)

@Composable
fun FlightProgressContent(
    progress: Float,
    step: FlightJourneyStep,
    modifier: Modifier = Modifier
) {
    // Force progress to stay between 0.0 and 1.0
    val targetProgress = progress.coerceIn(0f, 1f)

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        val totalWidth = maxWidth

        // 1. The Background Track & Progress Bar
        LinearProgressIndicator(
            progress = { targetProgress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp), // Adjust track thickness as needed
            strokeCap = StrokeCap.Round
        )

        // 2. Start Circle Indicator
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        )

        // 3. Airplane Emoji Thumb at the current progress position
        Box(
            modifier = Modifier
                .offset(x = totalWidth * targetProgress - 12.dp) // Subtract half the emoji's estimated width to center it
        ) {
            Text(
                text = "✈️",
                fontSize = 18.sp
            )
        }
    }
}


