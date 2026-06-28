package com.example.composecraft.features.vehicle.common


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// ─── Data models ─────────────────────────────────────────────────────────────

data class DetailRow(val label: String, val value: String)
data class MetricRow(val label: String, val value: String)

// ─── Preview data ─────────────────────────────────────────────────────────────

private val designSystemInfo = listOf(
    DetailRow("Status", "Active"),
    DetailRow("Contributors", "12 members"),
    DetailRow("Components", "84 total"),
    DetailRow("Updated", "Jun 5, 2026"),
)

private val gatewayMetrics = listOf(
    MetricRow("Health", "Degraded"),
    MetricRow("Latency p99", "840 ms"),
    MetricRow("Error rate", "2.3%"),
    MetricRow("Req / min", "1,204"),
)

// ─── Slot composables ─────────────────────────────────────────────────────────

@Composable
fun CardDetailRows(items: List<DetailRow>) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        items.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = row.label,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = row.value,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun MetricRows(data: List<MetricRow>) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        data.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = row.label,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = row.value,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun ActionButtons(
    onSecondary: () -> Unit,
    onPrimary: () -> Unit,
) {
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedButton(
            onClick = onSecondary,
            modifier = Modifier.weight(1f)
        ) {
            Text("Docs")
        }
        Button(
            onClick = onPrimary,
            modifier = Modifier.weight(1f)
        ) {
            Text("Open")
        }
    }
}

// ─── ExpandableCard ───────────────────────────────────────────────────────────

@Composable
fun ExpandableCard(
    title: String,
    subTitle: String,
    icon: ImageVector,
    content: @Composable () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "chevronRotation"      // fix: label required in Compose 1.5+
    )

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.animateContentSize(
                animationSpec = tween(durationMillis = 350, easing = EaseInOutCubic)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)  // fix: icon/text gap
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary           // fix: explicit tint
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = subTitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Icon(
                    imageVector = Icons.Default.ExpandMore,
                    contentDescription = if (expanded) "Collapse" else "Expand",  // fix: a11y
                    modifier = Modifier.rotate(rotation),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (expanded) {
//                HorizontalDivider()
                Box(modifier = Modifier.padding(16.dp)) {
                    content()
                }
            }
        }
    }
}

// ─── Preview ─────────────────────────────────────────────────────────────────

@Preview(showBackground = true, widthDp = 360)
@Composable
fun ExpandableCardPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ExpandableCard(
                title = "Design System",
                subTitle = "v2.4.1 · Stable",
                icon = Icons.Outlined.Palette
            ) {
                CardDetailRows(items = designSystemInfo)
                ActionButtons(
                    onSecondary = { /* open docs */ },
                    onPrimary = { /* open project */ }
                )
            }

            ExpandableCard(
                title = "API Gateway",
                subTitle = "prod-us-west",
                icon = Icons.Outlined.Warning
            ) {
                MetricRows(data = gatewayMetrics)
            }
        }
    }
}