package com.example.composecraft.presentation.features.disastercommandcenter.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.IconSpec
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.Incident
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.IncidentType
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.Severity

@Preview
@Composable
fun PreviewActiveIncidentCard() {
    val incident = Incident(
        type = IncidentType.FIRE,
        title = "Warehouse Fire",
        address = "Northbrook, Sector 7",
        severity = Severity.HIGH,
        numberOfTeams = 2,
        distanceInKm = 2.4,
        etaInMin = 8
    )
    ActiveIncidentCard(
        incident = incident,
        onClick = {}
    )
}

@Composable
fun ActiveIncidentCard(
    incident: Incident,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.width(250.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
        ),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IncidentIcon(incident.type.iconSpec)
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = incident.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = incident.address,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Severity reused as Icon style
                IncidentLabel(
                    icon = IconSpec(
                        emoji = incident.severity.name,
                        color = incident.type.iconSpec.color
                    )
                )
                InfoItem(
                    icon = Icons.Default.LocationOn,
                    label = "${incident.distanceInKm} km",
                    color = Color.Gray
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                InfoItem(
                    icon = Icons.Default.Shield,
                    label = "${incident.numberOfTeams} Teams",
                    color = incident.type.iconSpec.color
                )

                InfoItem(
                    icon = Icons.Default.Timer,
                    label = "${incident.etaInMin} min",
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
private fun InfoItem(
    icon: ImageVector,
    label: String,
    color: Color
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 4.dp)
                .height(14.dp),
            tint = color
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
    }
}
