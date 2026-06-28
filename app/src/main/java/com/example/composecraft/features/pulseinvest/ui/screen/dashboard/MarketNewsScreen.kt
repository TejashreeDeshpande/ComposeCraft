package com.example.composecraft.features.pulseinvest.ui.screen.dashboard

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
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.HomeViewModel
import com.example.composecraft.features.pulseinvest.ui.components.PulseScaffold
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun MarketNewsScreen(vm: HomeViewModel, onBack: () -> Unit) {
    val articles = listOf(
        Triple("🟢", "NVIDIA Surges on Strong Earnings & AI Demand", "Bloomberg · 2h ago"),
        Triple("📊", "Markets Rally as Tech Leads Gains", "CNBC · 3h ago"),
        Triple("🏦", "Fed Signals Possible Rate Cuts in Q3 2024", "Reuters · 5h ago"),
        Triple("⚡", "Tesla Secures Major European Manufacturing Deal", "WSJ · 6h ago"),
        Triple("💊", "Pharma Sector Outperforms on New Drug Approvals", "Bloomberg · 8h ago")
    )

    PulseScaffold(title = "Market News", showBack = true, onBack = onBack) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().background(PulseDark).padding(padding).padding(horizontal = 20.dp)) {
            item {
                Spacer(Modifier.height(8.dp))
                // Featured
                Box(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(PulseSurface)) {
                    Column {
                        Box(modifier = Modifier.fillMaxWidth().height(120.dp).background(PulseGreenBg), contentAlignment = Alignment.Center) {
                            Text("🟢", fontSize = 56.sp)
                        }
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text("TOP NEWS", color = PulseGreen, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(4.dp))
                            Text("NVIDIA Surges on Strong Earnings & AI Demand", color = PulseTextPrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp, lineHeight = 22.sp)
                            Spacer(Modifier.height(4.dp))
                            Text("Bloomberg · 2h ago", color = PulseTextSecondary, fontSize = 12.sp)
                            Spacer(Modifier.height(10.dp))
                            Box(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)).background(PulseGreenBg).padding(10.dp)) {
                                Text("📈 Impact on your portfolio: +\$842.35 (0.68%) estimated gain", color = PulseGreen, fontSize = 13.sp)
                            }
                        }
                    }
                }
                Spacer(Modifier.height(16.dp))
            }
            items(articles.drop(1)) { (emoji, title, meta) ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp), verticalAlignment = Alignment.Top) {
                    Box(modifier = Modifier.size(70.dp, 60.dp).clip(RoundedCornerShape(10.dp)).background(PulseSurface2), contentAlignment = Alignment.Center) {
                        Text(emoji, fontSize = 28.sp)
                    }
                    Spacer(Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(title, color = PulseTextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 13.sp, lineHeight = 18.sp)
                        Spacer(Modifier.height(4.dp))
                        Text(meta, color = PulseTextSecondary, fontSize = 11.sp)
                    }
                }
                HorizontalDivider(color = PulseSurface2, thickness = 0.5.dp)
            }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
