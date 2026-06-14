package com.example.composecraft.presentation.features.disastercommandcenter.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.Team
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.TeamLiveStatus
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.User
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.mockActiveIncidents

@Preview
@Composable
fun PreviewTeamInfoCard() {
    val team = Team(
        name = "Alpha Team",
        incident = mockActiveIncidents[0],
        status = TeamLiveStatus.EN_ROUTE,
        users = listOf(
            User(1, "Sarah Kim", "Fire Captain", "👩🏻‍🚒"),
            User(2, "David Ross", "Rescue Specialist", "👨🏽‍🚒"),
            User(3, "Emma Clark", "Medic", "👩🏼‍⚕️")
        )
    )
    TeamInfoCard(team = team)
}

@Composable
fun TeamInfoCard(
    team: Team,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = modifier.width(220.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        ),
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            team.incident.type.iconSpec.color.copy(alpha = 0.1f),
                            CircleShape
                        )
                        .padding(8.dp)
                ) {
                    IncidentIcon(team.incident.type.iconSpec)
                }
                
                Column {
                    Text(
                        text = team.name,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = team.incident.type.title,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Surface(
                modifier = Modifier.padding(vertical = 12.dp),
                color = team.status.color.copy(alpha = 0.1f),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = team.status.title.uppercase(),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.ExtraBold,
                    color = team.status.color,
                    letterSpacing = 0.5.sp
                )
            }

            Row(
                modifier = Modifier.padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(contentAlignment = Alignment.CenterStart) {
                    team.users.take(3).forEachIndexed { index, user ->
                        Box(modifier = Modifier.padding(start = (index * 20).dp)) {
                            AvatarCircle(user.avatar)
                        }
                    }
                }
                if (team.users.size > 3) {
                    Text(
                        text = "+${team.users.size - 3}",
                        modifier = Modifier.padding(start = (3 * 20 + 8).dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun AvatarCircle(emoji: String) {
    Surface(
        shape = CircleShape,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.size(28.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(2.dp)
        ) {
            Text(text = emoji, fontSize = 14.sp)
        }
    }
}
