package com.example.composecraft.features.pulseinvest.ui.screen.account

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.ui.navigation.PulseRoute
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun ProfileScreen(onNavigate: (String) -> Unit) {
    Scaffold(containerColor = PulseDark) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Brush.verticalGradient(listOf(PulseGreenBg, PulseDark)))
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(PulseGreen)
                            .border(3.dp, PulseGreen, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("TP", color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
                    }
                    Spacer(Modifier.width(16.dp))
                    Column {
                        Text("Tejashree Patil", color = PulseTextPrimary, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                        Text("tejashree@gmail.com", color = PulseTextSecondary, fontSize = 13.sp)
                    }
                }
            }
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Spacer(Modifier.height(4.dp))
                    MenuSection(
                        "Account", listOf(
                            MenuItem("👤", "Account Details", PulseRoute.Profile.route),
                            MenuItem("🏦", "Bank Accounts", PulseRoute.Profile.route),
                            MenuItem("🔒", "Security", PulseRoute.SecurityCenter.route),
                            MenuItem("🔔", "Notifications", PulseRoute.Notifications.route),
                            MenuItem("📋", "Statements & Reports", PulseRoute.Statements.route),
                            MenuItem("📄", "Tax Documents", PulseRoute.Statements.route)
                        ), onNavigate
                    )
                    Spacer(Modifier.height(8.dp))
                    MenuSection(
                        "Actions", listOf(
                            MenuItem("💵", "Deposit Funds", PulseRoute.DepositFunds.route),
                            MenuItem("🏧", "Withdraw Funds", PulseRoute.WithdrawFunds.route),
                            MenuItem("🎓", "Learning Hub", PulseRoute.LearningHub.route)
                        ), onNavigate
                    )
                    Spacer(Modifier.height(8.dp))
                    MenuSection(
                        "More", listOf(
                            MenuItem("⚙️", "Settings", PulseRoute.Settings.route),
                            MenuItem("❓", "Help & Support", PulseRoute.HelpCenter.route)
                        ), onNavigate
                    )
                }
            }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

data class MenuItem(val emoji: String, val label: String, val route: String)

@Composable
fun MenuSection(title: String, items: List<MenuItem>, onNavigate: (String) -> Unit) {
    Text(title.uppercase(), color = PulseTextMuted, fontSize = 12.sp, modifier = Modifier.padding(vertical = 8.dp))
    items.forEachIndexed { i, item ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigate(item.route) }
                .padding(vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Box(
                    modifier = Modifier.size(36.dp).clip(RoundedCornerShape(10.dp)).background(PulseSurface),
                    contentAlignment = Alignment.Center
                ) { Text(item.emoji, fontSize = 18.sp) }
                Text(item.label, color = PulseTextPrimary, fontSize = 15.sp)
            }
            Text("›", color = PulseTextMuted, fontSize = 18.sp)
        }
        if (i < items.size - 1) HorizontalDivider(color = PulseSurface2, thickness = 0.5.dp)
    }
}
