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
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun NotificationsScreen(vm: NotificationViewModel, onBack: () -> Unit) {
    val notifications by vm.notifications.collectAsState()

    PulseScaffold(title = "Notifications", showBack = true, onBack = onBack) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().background(PulseDark).padding(padding).padding(horizontal = 20.dp)) {
            item {
                Spacer(Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = vm::markAllRead) { Text("Mark all as read", color = PulseGreen, fontSize = 13.sp) }
                }
            }
            items(notifications) { notif ->
                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp).clip(RoundedCornerShape(12.dp))
                    .background(PulseSurface).padding(14.dp)) {
                    Box(modifier = Modifier.size(44.dp).clip(RoundedCornerShape(50)).background(PulseDark), contentAlignment = Alignment.Center) {
                        Text(notif.emoji, fontSize = 22.sp)
                    }
                    Spacer(Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(notif.title, color = PulseTextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        Spacer(Modifier.height(2.dp))
                        Text(notif.body, color = PulseTextSecondary, fontSize = 13.sp, lineHeight = 18.sp)
                        Spacer(Modifier.height(4.dp))
                        Text(notif.timeAgo, color = PulseTextMuted, fontSize = 11.sp)
                    }
                    if (!notif.isRead) {
                        Box(modifier = Modifier.size(8.dp).clip(RoundedCornerShape(50)).background(PulseGreen))
                    }
                }
            }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
