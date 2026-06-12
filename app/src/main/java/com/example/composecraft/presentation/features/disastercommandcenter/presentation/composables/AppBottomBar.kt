package com.example.composecraft.presentation.features.disastercommandcenter.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.BottomBarCard
import com.example.composecraft.presentation.features.disastercommandcenter.data.model.bottomBarCards

@Preview
@Composable
fun PreviewAppBottomBar() {
    AppBottomBar(
        tabs = bottomBarCards,
        onTabSelected = {},
        selectedCard = bottomBarCards[0]
    )
}

@Composable
fun AppBottomBar(
    tabs: List<BottomBarCard>,
    selectedCard: BottomBarCard,
    onTabSelected: (BottomBarCard) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomAppBar(
        modifier = modifier.fillMaxWidth(),
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                tabs.forEach { card ->
                    BottomNavCard(
                        bottomCard = card,
                        isSelected = selectedCard == card,
                        onClick = { onTabSelected(card) }
                    )
                }
            }
        }
    )
}

@Composable
private fun BottomNavCard(
    bottomCard: BottomBarCard,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.surface else Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = if (isSelected) 24.dp else 16.dp,
                vertical = 16.dp
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = bottomCard.icon,
                contentDescription = bottomCard.title
            )
            Text(
                text = bottomCard.title,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}