package com.example.composecraft.features.pulseinvest.ui.screen.support

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
import com.example.composecraft.features.pulseinvest.domain.model.MessageSender
import com.example.composecraft.features.pulseinvest.presentation.viewmodel.SupportViewModel
import com.example.composecraft.features.pulseinvest.ui.components.PulseScaffold
import com.example.composecraft.features.pulseinvest.ui.theme.*

@Composable
fun SupportChatScreen(vm: SupportViewModel, onBack: () -> Unit) {
    val messages by vm.messages.collectAsState()
    val inputText by vm.inputText.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) { if (messages.isNotEmpty()) listState.animateScrollToItem(messages.size - 1) }

    PulseScaffold(title = "Support Chat", showBack = true, onBack = onBack) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            LazyColumn(state = listState, modifier = Modifier.weight(1f).background(PulseDark).padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp), contentPadding = PaddingValues(vertical = 16.dp)) {
                items(messages) { msg ->
                    if (msg.sender == MessageSender.USER) {
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                            Box(modifier = Modifier.clip(RoundedCornerShape(16.dp, 16.dp, 4.dp, 16.dp)).background(PulseGreen).padding(12.dp, 10.dp)) {
                                Text(msg.text, color = Color.Black, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                            }
                        }
                    } else {
                        Column(modifier = Modifier.fillMaxWidth(0.8f)) {
                            Text("Support Agent", color = PulseTextMuted, fontSize = 11.sp)
                            Spacer(Modifier.height(4.dp))
                            Box(modifier = Modifier.clip(RoundedCornerShape(4.dp, 16.dp, 16.dp, 16.dp)).background(PulseSurface).padding(14.dp)) {
                                Text(msg.text, color = PulseTextPrimary, fontSize = 14.sp, lineHeight = 20.sp)
                            }
                        }
                    }
                }
            }
            Row(modifier = Modifier.background(PulseDark).padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(value = inputText, onValueChange = vm::onInputChange,
                    placeholder = { Text("Type your message...", color = PulseTextMuted) },
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PulseGreen, unfocusedBorderColor = PulseBorder, focusedContainerColor = PulseSurface2, unfocusedContainerColor = PulseSurface2, focusedTextColor = PulseTextPrimary, unfocusedTextColor = PulseTextPrimary),
                    shape = RoundedCornerShape(24.dp), singleLine = true)
                IconButton(onClick = vm::sendMessage,
                    modifier = Modifier.size(44.dp).clip(RoundedCornerShape(50)).background(PulseGreen)) {
                    Text("➤", color = Color.Black, fontSize = 16.sp)
                }
            }
        }
    }
}
