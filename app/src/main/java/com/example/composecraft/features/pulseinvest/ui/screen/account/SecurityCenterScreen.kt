package com.example.composecraft.features.pulseinvest.ui.screen.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.ui.components.PulseScaffold
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun SecurityCenterScreen(onBack: () -> Unit) {
    PulseScaffold(title = "Security Center", showBack = true, onBack = onBack) { padding ->
        Column(modifier = Modifier.fillMaxSize().background(PulseDark).padding(padding).padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Spacer(Modifier.height(8.dp))
            SecurityCard("Two-Factor Authentication", "Secure your account with 2FA", true)
            SecurityCard("Login Alerts", "Get notified on new logins", true)
            Spacer(Modifier.height(8.dp))
            listOf(Triple("Change PIN", "", false), Triple("Change Password", "", false), Triple("Manage Devices", "2 devices logged in", false)).forEach { (label, sub, _) ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 14.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text(label, color = PulseTextPrimary, fontSize = 15.sp)
                        if (sub.isNotEmpty()) Text(sub, color = PulseTextSecondary, fontSize = 12.sp)
                    }
                    Text("›", color = PulseTextMuted, fontSize = 18.sp)
                }
                HorizontalDivider(color = PulseSurface2, thickness = 0.5.dp)
            }
        }
    }
}

@Composable
fun SecurityCard(title: String, desc: String, enabled: Boolean) {
    Column(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(PulseSurface).padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(title, color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Box(modifier = Modifier.clip(RoundedCornerShape(10.dp)).background(if (enabled) PulseGreenBg else PulseSurface2).padding(horizontal = 8.dp, vertical = 3.dp)) {
                Text(if (enabled) "Enabled" else "Disabled", color = if (enabled) PulseGreen else PulseTextMuted, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(Modifier.height(4.dp))
        Text(desc, color = PulseTextSecondary, fontSize = 13.sp)
    }
}
