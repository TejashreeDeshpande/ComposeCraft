package com.example.composecraft.presentation.features.disastercommandcenter.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.Team
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.TeamLiveStatus
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.User
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.mockActiveIncidents

@Preview
@Composable
fun PreviewTeamInfoCard() {
    val team = Team(
        name = "Alpha Rescue Unit",
        incident = mockActiveIncidents[0],
        status = TeamLiveStatus.EN_ROUTE,
        users = listOf(
            User(
                id = 1,
                name = "Sarah Kim",
                avatar = "👩🏻‍🚒"
            ),
            User(
                id = 2,
                name = "David Ross",
                avatar = "👨🏽‍🚒"
            ),
            User(
                id = 3,
                name = "Emma Clark",
                avatar = "👩🏼‍⚕️"
            )
        )
    )
    TeamInfoCard(team = team)
}

@Composable
fun TeamInfoCard(team: Team) {
    Card(
        modifier = Modifier.width(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row {
                IncidentIcon(team.incident.type.iconSpec)
                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text(
                        text = team.name,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = team.incident.type.title,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = team.status.title,
                        style = MaterialTheme.typography.labelSmall,
                        color = team.status.color,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }

            LazyRow {
                items(team.users) { user ->
                    AvatarCircle(user.avatar)
                }
            }
        }
    }
}

@Composable
fun AvatarCircle(emoji: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
    ) {
        Text(text = emoji)
    }
}