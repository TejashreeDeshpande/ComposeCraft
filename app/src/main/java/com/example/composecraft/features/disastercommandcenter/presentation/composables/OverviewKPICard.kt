package com.example.composecraft.features.disastercommandcenter.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.features.disastercommandcenter.data.model.KpiModel
import com.example.composecraft.features.disastercommandcenter.data.model.KpiType
import com.example.composecraft.ui.theme.DisasterTheme

@Preview
@Composable
fun PreviewOverviewKPICard() {
    DisasterTheme {
        OverviewKPICard(
            KpiModel(
                type = KpiType.INCIDENTS,
                title = "Incidents",
                value = "23",
                trend = "+8",
                trendSuffix = "today"
            )
        )
    }
}

@Composable
fun OverviewKPICard(
    model: KpiModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(
            1.dp,
            model.type.iconSpec.color.copy(alpha = 0.1f)
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .background(
                        model.type.iconSpec.color.copy(alpha = 0.1f),
                        MaterialTheme.shapes.medium
                    )
                    .padding(6.dp)
            ) {
                IncidentIcon(model.type.iconSpec)
            }
            
            Column {
                Text(
                    text = model.value,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = model.title,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .background(
                        if (model.trend.startsWith("+")) Color(0xFF22C55E).copy(alpha = 0.1f)
                        else Color(0xFFEF4444).copy(alpha = 0.1f),
                        CircleShape
                    )
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = model.trend,
                    color = if (model.trend.startsWith("+")) Color(0xFF22C55E) else Color(0xFFEF4444),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
