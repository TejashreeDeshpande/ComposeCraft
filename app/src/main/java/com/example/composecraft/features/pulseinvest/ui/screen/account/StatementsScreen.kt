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
import com.example.composecraft.features.pulseinvest.domain.model.StatementType
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.AccountViewModel
import com.example.composecraft.features.pulseinvest.ui.components.PulseScaffold
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun StatementsScreen(vm: AccountViewModel, onBack: () -> Unit) {
    val statements by vm.statements.collectAsState()
    val monthly = statements.filter { it.type == StatementType.MONTHLY }
    val tax = statements.filter { it.type == StatementType.TAX }

    PulseScaffold(title = "Statements & Reports", showBack = true, onBack = onBack) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().background(PulseDark).padding(padding).padding(horizontal = 20.dp)) {
            item { Spacer(Modifier.height(8.dp)); Text("MONTHLY STATEMENTS", color = PulseTextMuted, fontSize = 12.sp); Spacer(Modifier.height(8.dp)) }
            items(monthly) { s ->
                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp).clip(RoundedCornerShape(12.dp)).background(PulseSurface).padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(42.dp).clip(RoundedCornerShape(10.dp)).background(PulseDark), contentAlignment = Alignment.Center) { Text("📄", fontSize = 22.sp) }
                    Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                        Text(s.label, color = PulseTextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        Text(s.period, color = PulseTextSecondary, fontSize = 12.sp)
                    }
                    Box(modifier = Modifier.size(32.dp).clip(RoundedCornerShape(8.dp)).background(PulseGreenBg), contentAlignment = Alignment.Center) { Text("⬇", color = PulseGreen, fontSize = 16.sp) }
                }
            }
            item { Spacer(Modifier.height(8.dp)); Text("TAX DOCUMENTS", color = PulseTextMuted, fontSize = 12.sp); Spacer(Modifier.height(8.dp)) }
            items(tax) { s ->
                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp).clip(RoundedCornerShape(12.dp)).background(PulseSurface).padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(42.dp).clip(RoundedCornerShape(10.dp)).background(PulseDark), contentAlignment = Alignment.Center) { Text("🧾", fontSize = 22.sp) }
                    Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                        Text(s.label, color = PulseTextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        Text(s.period, color = PulseTextSecondary, fontSize = 12.sp)
                    }
                    Box(modifier = Modifier.size(32.dp).clip(RoundedCornerShape(8.dp)).background(PulseGreenBg), contentAlignment = Alignment.Center) { Text("⬇", color = PulseGreen, fontSize = 16.sp) }
                }
            }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}
