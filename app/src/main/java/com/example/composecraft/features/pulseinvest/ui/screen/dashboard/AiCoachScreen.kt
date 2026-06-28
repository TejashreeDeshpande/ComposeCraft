package com.example.composecraft.features.pulseinvest.ui.screen.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecraft.features.pulseinvest.ui.theme.*

data class AiMessage(val text: String, val isUser: Boolean)

@Composable
fun AiCoachScreen() {
    var input by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf(
        AiMessage("Hi Tejashree! 👋 How can I help you today?", false)
    )}
    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) { listState.animateScrollToItem(messages.size - 1) }

    Scaffold(containerColor = PulseDark) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            // Header
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp).fillMaxWidth()) {
                Text("🤖 AI Coach", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = PulseTextPrimary)
                Text("Powered by PULSE Intelligence", fontSize = 13.sp, color = PulseTextSecondary)
            }
            HorizontalDivider(color = PulseSurface2)
            // Messages
            LazyColumn(state = listState, modifier = Modifier.weight(1f).padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp), contentPadding = PaddingValues(vertical = 16.dp)) {
                items(messages) { msg ->
                    if (msg.isUser) {
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                            Box(modifier = Modifier.clip(RoundedCornerShape(16.dp, 16.dp, 4.dp, 16.dp))
                                .background(PulseGreen).padding(12.dp, 10.dp)) {
                                Text(msg.text, color = Color.Black, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                            }
                        }
                    } else {
                        Column(modifier = Modifier.fillMaxWidth(0.8f)) {
                            Text("PULSE AI", color = PulseGreen, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(4.dp))
                            Box(modifier = Modifier.clip(RoundedCornerShape(4.dp, 16.dp, 16.dp, 16.dp))
                                .background(PulseSurface).padding(14.dp)) {
                                Text(msg.text, color = PulseTextPrimary, fontSize = 14.sp, lineHeight = 20.sp)
                            }
                        }
                    }
                }
            }
            // Suggestions
            Row(modifier = Modifier.padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Market outlook", "Top picks").forEach { s ->
                    SuggestionChip(onClick = { messages.add(AiMessage(s, true)); messages.add(AiMessage("Great question! Let me analyze that for you...", false)) }, label = { Text(s, fontSize = 12.sp) },
                        colors = SuggestionChipDefaults.suggestionChipColors(containerColor = PulseGreenBg, labelColor = PulseGreen))
                }
            }
            Spacer(Modifier.height(8.dp))
            // Input
            Row(modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(value = input, onValueChange = { input = it },
                    placeholder = { Text("Ask anything...", color = PulseTextMuted) },
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PulseGreen, unfocusedBorderColor = PulseBorder, focusedContainerColor = PulseSurface2, unfocusedContainerColor = PulseSurface2, focusedTextColor = PulseTextPrimary, unfocusedTextColor = PulseTextPrimary),
                    shape = RoundedCornerShape(24.dp), singleLine = true)
                IconButton(onClick = { if (input.isNotBlank()) { messages.add(AiMessage(input, true)); messages.add(AiMessage("I'm analyzing your question: \"$input\"...", false)); input = "" } },
                    modifier = Modifier.size(44.dp).clip(RoundedCornerShape(50)).background(PulseGreen)) {
                    Text("➤", color = Color.Black, fontSize = 16.sp)
                }
            }
        }
    }
}
