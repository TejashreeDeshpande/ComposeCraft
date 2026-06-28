package com.example.composecraft.features.pulseinvest.ui.screen.support

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.ui.components.PulseScaffold
import com.example.composecraft.features.pulseinvest.ui.components.PulseTextField
import com.example.composecraft.features.pulseinvest.ui.theme.*
import androidx.compose.runtime.*

@Composable
fun HelpCenterScreen(onBack: () -> Unit, onContactSupport: () -> Unit) {
    var search by remember { mutableStateOf("") }
    val topics = listOf("Getting Started","Deposits & Withdrawals","Trading & Orders","Account & Security","Billing & Fees")

    PulseScaffold(title = "Help Center", showBack = true, onBack = onBack) { padding ->
        Column(modifier = Modifier.fillMaxSize().background(PulseDark).padding(padding).padding(horizontal = 20.dp)) {
            Spacer(Modifier.height(8.dp))
            PulseTextField(search, { search = it }, "Search help articles...")
            Spacer(Modifier.height(20.dp))
            Text("POPULAR TOPICS", color = PulseTextMuted, fontSize = 12.sp)
            Spacer(Modifier.height(8.dp))
            topics.forEach { topic ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 14.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(topic, color = PulseTextPrimary, fontSize = 15.sp)
                    Text("›", color = PulseTextMuted, fontSize = 18.sp)
                }
                HorizontalDivider(color = PulseSurface2, thickness = 0.5.dp)
            }
            Row(modifier = Modifier.fillMaxWidth().clickable { onContactSupport() }.padding(vertical = 14.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text("Contact Support", color = PulseGreen, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                Text("›", color = PulseGreen, fontSize = 18.sp)
            }
        }
    }
}
