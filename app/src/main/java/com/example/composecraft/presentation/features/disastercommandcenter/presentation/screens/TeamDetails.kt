package com.example.composecraft.presentation.features.disastercommandcenter.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.presentation.components.jun4.AvatarCircle
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.Team
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.User
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.mockTeams

@Preview
@Composable
fun PreviewTeamDetails() {
    TeamDetails(team = mockTeams[0])
}

@Composable
fun TeamDetails(
    team: Team
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = team.name,
            style = MaterialTheme.typography.headlineMedium
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = team.users) { person ->
                PersonRow(person)
            }
        }
    }
}

@Composable
fun PersonRow(person: User) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AvatarCircle(person.avatar)
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(person.name)
                Text(
                    person.title,
                    fontSize = 10.sp
                )
            }
        }
    }
}