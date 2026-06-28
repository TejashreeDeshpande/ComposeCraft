package com.example.composecraft.features.pulseinvest.ui.screen.account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.ui.components.PulseScaffold
import com.example.composecraft.features.pulseinvest.ui.components.PulseSwitch
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    var darkMode by remember { mutableStateOf(true) }

    PulseScaffold(title = "Settings", showBack = true, onBack = onBack) { padding ->
        Column(modifier = Modifier.fillMaxSize().background(PulseDark).padding(padding).padding(horizontal = 20.dp)) {
            SettingsSection("App Preferences")
            SettingsRow("Theme", "Dark >")
            SettingsRow("Language", "English >")
            SettingsSection("Privacy")
            SettingsRow("Data & Privacy")
            SettingsRow("Privacy Policy")
            SettingsSection("Legal")
            SettingsRow("Terms & Conditions")
            SettingsRow("Data Settings")
            SettingsSection("Display")
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 14.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("Dark Mode", color = PulseTextPrimary, fontSize = 15.sp)
                PulseSwitch(checked = darkMode, onCheckedChange = { darkMode = it })
            }
            SettingsSection("About")
            SettingsRow("About")
            Spacer(Modifier.weight(1f))
            Text("App Version 1.0.01", color = PulseTextMuted, fontSize = 12.sp, modifier = Modifier.align(Alignment.CenterHorizontally).padding(20.dp))
        }
    }
}

@Composable
fun SettingsSection(title: String) {
    Text(title.uppercase(), color = PulseTextMuted, fontSize = 11.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp, bottom = 4.dp))
}

@Composable
fun SettingsRow(label: String, value: String = "") {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 14.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(label, color = PulseTextPrimary, fontSize = 15.sp)
        Text(if (value.isEmpty()) "›" else value, color = PulseTextMuted, fontSize = 14.sp)
    }
    HorizontalDivider(color = PulseSurface2, thickness = 0.5.dp)
}
