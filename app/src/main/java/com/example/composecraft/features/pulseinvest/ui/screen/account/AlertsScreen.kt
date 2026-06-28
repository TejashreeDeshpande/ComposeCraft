package com.example.composecraft.features.pulseinvest.ui.screen.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.NotificationViewModel
import com.example.composecraft.features.pulseinvest.ui.components.PulseScaffold
import com.example.composecraft.features.pulseinvest.ui.components.PulseSwitch
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun AlertsScreen(vm: NotificationViewModel, onBack: () -> Unit) {
    val alerts by vm.alerts.collectAsState()

    PulseScaffold(title = "Alerts", showBack = true, onBack = onBack) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().background(PulseDark).padding(padding).padding(horizontal = 20.dp)) {
            item {
                Spacer(Modifier.height(8.dp))
                Text("PRICE ALERTS", color = PulseTextMuted, fontSize = 12.sp)
                Spacer(Modifier.height(8.dp))
            }
            items(alerts) { alert ->
                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp).clip(RoundedCornerShape(12.dp)).background(PulseSurface).padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(42.dp).clip(RoundedCornerShape(10.dp)).background(PulseDark), contentAlignment = Alignment.Center) {
                        Text(alert.emoji, fontSize = 22.sp)
                    }
                    Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                        Text(alert.symbol, color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text(alert.condition, color = PulseTextSecondary, fontSize = 13.sp)
                    }
                    PulseSwitch(checked = alert.isEnabled, onCheckedChange = { vm.toggleAlert(alert.id, it) })
                }
            }
            item {
                Spacer(Modifier.height(16.dp))
                Text("NEWS ALERTS", color = PulseTextMuted, fontSize = 12.sp)
                Spacer(Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(PulseSurface).padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(42.dp).clip(RoundedCornerShape(10.dp)).background(PulseDark), contentAlignment = Alignment.Center) { Text("📰", fontSize = 22.sp) }
                    Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                        Text("Market News", color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text("Breaking news and updates", color = PulseTextSecondary, fontSize = 13.sp)
                    }
                    PulseSwitch(checked = true, onCheckedChange = {})
                }
                Spacer(Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(PulseSurface).padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(42.dp).clip(RoundedCornerShape(10.dp)).background(PulseDark), contentAlignment = Alignment.Center) { Text("⭐", fontSize = 22.sp) }
                    Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                        Text("Watchlist Updates", color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text("Alerts for your watchlist", color = PulseTextSecondary, fontSize = 13.sp)
                    }
                    PulseSwitch(checked = true, onCheckedChange = {})
                }
            }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
